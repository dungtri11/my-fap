package com.portal.fap.service;

import com.portal.fap.dto.request.UserDetailDto;
import com.portal.fap.dto.response.UserDetailResponseDto;
import com.portal.fap.entity.User;

import java.io.IOException;

public interface UserService {
    public UserDetailResponseDto showUserDetail(Long userid);
    public UserDetailResponseDto addUser(UserDetailDto dto) throws IOException;
    public UserDetailResponseDto editUser(UserDetailDto dto, Long userid) throws IOException;
    public User findById(Long id);
    public User save(User user);
}
