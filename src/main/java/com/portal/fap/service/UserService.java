package com.portal.fap.service;

import com.portal.fap.dto.Response;
import com.portal.fap.dto.request.AuthenticationInfo;
import com.portal.fap.dto.request.RegistrationInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public Response authenticate(AuthenticationInfo info);

    public Response register(RegistrationInfo info);
}
