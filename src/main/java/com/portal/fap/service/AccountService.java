package com.portal.fap.service;

import com.portal.fap.dto.request.RequestLoginDto;
import com.portal.fap.dto.request.UserDetailDto;
import com.portal.fap.dto.response.ResponseLoginDto;
import com.portal.fap.dto.response.ResponseRegisterDto;
import com.portal.fap.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    public ResponseLoginDto authenticate(RequestLoginDto loginDto);
    public ResponseRegisterDto register(Account account);
    public Account save(Account account);
    public Account createDefaultAccount(UserDetailDto dto);
}
