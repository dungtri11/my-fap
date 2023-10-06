package com.portal.fap.service.impl;

import com.portal.fap.dto.request.UserDetailDto;
import com.portal.fap.dto.response.UserDetailResponseDto;
import com.portal.fap.entity.User;
import com.portal.fap.exception.BadRequestResponseException;
import com.portal.fap.exception.NotFoundResponseException;
import com.portal.fap.repository.CIInformationRepository;
import com.portal.fap.repository.UserRepository;
import com.portal.fap.service.AccountService;
import com.portal.fap.service.ImageService;
import com.portal.fap.service.UserService;
import com.portal.fap.validation.ValidUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final String IMAGE_REPO_URI = "http://localhost:8080/image/";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CIInformationRepository ciInformationRepository;
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
    public UserDetailResponseDto addUser(UserDetailDto dto) throws IOException {
        User user = User.builder()
                .account(accountService.createDefaultAccount(dto))
                .ciInformation(dto.getInformation())
                .phones(dto.getPhones())
                .image(imageService.saveImage(dto.getImageFile(), null))
                .build();
        return new UserDetailResponseDto(save(user));
    }

    @Override
    public UserDetailResponseDto editUser(UserDetailDto dto, Long userid) throws IOException {
        User user = findById(userid);
        Long imageId = (user.getImage() != null ? user.getImage().getId() : null);
        if (dto.getImageFile() != null) {
            user.setImage(imageService.saveImage(dto.getImageFile(), imageId));
        }
        if (dto.getPhones() != null) {
            user.setPhones(dto.getPhones());
        }
        if (dto.getInformation() != null) {
            user.setCiInformation(dto.getInformation());
        }
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
        if (ciInformationRepository.existsByIdCard(user.getCiInformation().getIdCard()) && user.getId() == 0) {
            invalids.add("212 : User id card must be unique");
        }
        if (invalids.size() != 0) {
            String asString = invalids.stream().collect(Collectors.joining("; "));
            throw new BadRequestResponseException(asString);
        }
            return userRepository.save(user);
    }
}
