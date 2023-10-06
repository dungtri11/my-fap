package com.portal.fap.service;

import com.portal.fap.dto.request.UserDetailRequestDto;
import com.portal.fap.dto.response.UserDetailResponseDto;
import com.portal.fap.entity.User;

import java.io.IOException;

public interface UserService {
    public UserDetailResponseDto showUserDetail(Long userid);
    public UserDetailResponseDto addUser(UserDetailRequestDto dto) throws IOException;
    public UserDetailResponseDto editUser(UserDetailRequestDto dto, Long userid) throws IOException;
    public User findById(Long id);
    public User save(User user);
}
