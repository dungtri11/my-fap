package com.portal.fap.service;

import com.portal.fap.dto.request.UserDetailRequestDto;
import com.portal.fap.dto.response.UserDetailResponseDto;
import com.portal.fap.entity.User;

public interface UserService {
    public UserDetailResponseDto showUserDetail(Long userid);
    public UserDetailResponseDto addUser(UserDetailRequestDto dto);
    public UserDetailResponseDto editUser(UserDetailRequestDto dto, Long userid);
    public User findById(Long id);
    public User save(User user);
}
