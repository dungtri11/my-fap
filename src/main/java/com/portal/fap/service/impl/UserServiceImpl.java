package com.portal.fap.service.impl;

import com.portal.fap.dto.request.UserDetailRequestDto;
import com.portal.fap.dto.response.UserDetailResponseDto;
import com.portal.fap.entity.Account;
import com.portal.fap.entity.Authority;
import com.portal.fap.entity.User;
import com.portal.fap.exception.BadRequestResponseException;
import com.portal.fap.exception.NotFoundResponseException;
import com.portal.fap.repository.UserRepository;
import com.portal.fap.service.AccountService;
import com.portal.fap.service.UserService;
import com.portal.fap.utils.EmailUtils;
import com.portal.fap.utils.UsernameUtils;
import com.portal.fap.validation.ValidUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final String IMAGE_REPO_URI = "http://localhost:8080/image/";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetailResponseDto showUserDetail(Long userid) {
        User user = findById(userid);
        UserDetailResponseDto dto = new UserDetailResponseDto(user);
        if (user.getImage() != null) {
            dto.setImage(IMAGE_REPO_URI + user.getImage().getName());
        }
        return dto;
    }

    @Override
    public UserDetailResponseDto addUser(UserDetailRequestDto dto) {
        Account account = Account.builder()
                .username(UsernameUtils.generateUsernameFromName(dto.getName()))
                .email(EmailUtils.generateEmailFromName(dto.getName()))
                .password("ABD@#$123")
                .authorities(Set.of(new Authority("STUDENT")))
                .build();
        account = accountService.save(account);
        User user = User.builder()
                .account(account)
                .idCard(dto.getIdCard())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .phones(dto.getPhones())
                .fullName(dto.getName())
                .build();
        return new UserDetailResponseDto(save(user));
    }

    @Override
    public UserDetailResponseDto editUser(UserDetailRequestDto dto, Long userid) {
        User user = User.builder()
                .id(userid)
                .idCard(dto.getIdCard())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .phones(dto.getPhones())
                .fullName(dto.getName())
                .build();
        return new UserDetailResponseDto(save(user));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundResponseException("301 : Can't find user resource"));
    }

    @Override
    public User save(User user) {
        List<String> invalids = ValidUser.getUserInvalids(user);
        if (userRepository.existsByIdCard(user.getIdCard()) && user.getId() == 0) {
            invalids.add("212 : User id card must be unique");
        }
        if (invalids.size() != 0) {
            String asString = invalids.stream().collect(Collectors.joining("; "));
            throw new BadRequestResponseException(asString);
        }
            return userRepository.save(user);
    }
}
