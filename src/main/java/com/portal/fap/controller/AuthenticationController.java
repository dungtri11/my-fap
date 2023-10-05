package com.portal.fap.controller;

import com.portal.fap.dto.request.RequestLoginDto;
import com.portal.fap.entity.Account;
import com.portal.fap.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody RequestLoginDto requestLoginDto) {
        return ResponseEntity.ok(accountService.authenticate(requestLoginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registers(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.register(account));
    }


}
