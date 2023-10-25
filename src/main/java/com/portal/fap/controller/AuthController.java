package com.portal.fap.controller;

import com.portal.fap.dto.Response;
import com.portal.fap.dto.request.AuthenticationInfo;
import com.portal.fap.dto.request.RegistrationInfo;
import com.portal.fap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationInfo info) {
        return ResponseEntity.ok(userService.authenticate(info));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationInfo info) {
        return ResponseEntity.ok(userService.register(info));
    }
}
