package com.portal.fap.service.impl;

import com.portal.fap.common.Authority;
import com.portal.fap.dto.request.RequestLoginDto;
import com.portal.fap.dto.request.UserDetailDto;
import com.portal.fap.dto.response.ResponseLoginDto;
import com.portal.fap.dto.response.ResponseRegisterDto;
import com.portal.fap.entity.Account;
import com.portal.fap.exception.BadRequestResponseException;
import com.portal.fap.utils.EmailUtils;
import com.portal.fap.utils.JwtUtils;
import com.portal.fap.repository.AccountRepository;
import com.portal.fap.service.AccountService;
import com.portal.fap.utils.UsernameUtils;
import com.portal.fap.validation.ValidAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public ResponseLoginDto authenticate(RequestLoginDto requestLoginDto) {
        UserDetails account = loadUserByUsername(requestLoginDto.getUsername());
        manager.authenticate(new UsernamePasswordAuthenticationToken(account, requestLoginDto.getPassword()));
        String token = jwtUtils.generateToken(account);
        Date expireTime = jwtUtils.extractExpiration(token);
        return new ResponseLoginDto(token, expireTime, requestLoginDto.getUsername());
    }

    @Override
    public ResponseRegisterDto register(Account account) {
        save(account);
        String token = jwtUtils.generateToken(account);
        return new ResponseRegisterDto(account.getUsername(), account.getEmail(), token);
    }

    @Override
    public Account save(Account account) {
        List<String> invalids = ValidAccount.getAccountInvalids(account);
        if (accountRepository.existsByUsername(account.getUsername()) && account.getId() == 0) {
            invalids.add("109 : Username must be unique");
        }
        if (accountRepository.existsByEmail(account.getEmail()) && account.getId() == 0) {
            invalids.add("110 : Email must be unique");
        }
        if (invalids.size() != 0) {
            String asString = invalids.stream().collect(Collectors.joining("; "));
            throw new BadRequestResponseException(asString);
        }
        account.setPassword(encoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Account createDefaultAccount(UserDetailDto dto) {
        return Account.builder()
                .username(UsernameUtils.generateUsernameFromName(dto.getInformation().getFullName()))
                .email(EmailUtils.generateEmailFromName(dto.getInformation().getFullName()))
                .password(encoder.encode("ABD@#$123"))
                .authorities(Set.of(Authority.STUDENT))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User's authentication information isn't correct"));
        return account;
    }
}
