package com.portal.fap.service.impl;

import com.portal.fap.common.Authority;
import com.portal.fap.common.Status;
import com.portal.fap.dto.Response;
import com.portal.fap.dto.request.AuthenticationInfo;
import com.portal.fap.dto.request.RegistrationInfo;
import com.portal.fap.dto.response.AuthResponseDto;
import com.portal.fap.entity.Profile;
import com.portal.fap.entity.User;
import com.portal.fap.exception.NotFoundResponseException;
import com.portal.fap.repository.ProfileRepository;
import com.portal.fap.repository.UserRepository;
import com.portal.fap.service.UserService;
import com.portal.fap.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = profileRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new NotFoundResponseException("101 : NOT_FOUND"))
                .getBelongToUser();
        user.setCredential(username);
        return user;
    }

    @Override
    public Response<AuthResponseDto> authenticate(AuthenticationInfo info) {
        UserDetails userDetails = loadUserByUsername(info.getCredential());
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDetails, info.getPassword()));
        String token = jwtUtils.generateToken(userDetails);
        Date expiredTime = jwtUtils.extractExpiration(token);
        AuthResponseDto data = AuthResponseDto.construct(token, expiredTime);
        return new Response<>(Status.AUTHENTICATION_SUCCESSFUL, data);
    }

    @Override
    @Transactional
    public Response<AuthResponseDto> register(RegistrationInfo info) {
        List<Authority> authorities = new ArrayList<>(Arrays.asList(Authority.USER));
        Profile profile = Profile.builder()
                .email(info.getEmail())
                .username(info.getUsername())
                .build();
        User user = User.builder()
                .password(passwordEncoder.encode(info.getPassword()))
                .authorities(authorities)
                .profile(profile)
                .build();

        userRepository.save(user);
        /*
            return token for authentication
         */
        user.setCredential(info.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, info.getPassword()));
        String token = jwtUtils.generateToken(user);
        Date expiredTime = jwtUtils.extractExpiration(token);
        AuthResponseDto data = AuthResponseDto.construct(token, expiredTime);
        return new Response<>(Status.AUTHENTICATION_SUCCESSFUL, data);
    }
}
