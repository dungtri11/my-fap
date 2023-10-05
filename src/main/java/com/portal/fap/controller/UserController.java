package com.portal.fap.controller;

import com.portal.fap.dto.request.UserDetailRequestDto;
import com.portal.fap.dto.response.UserDetailResponseDto;
import com.portal.fap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody UserDetailRequestDto dto) {
        UserDetailResponseDto userDetailResponseDto = userService.addUser(dto);
        return ResponseEntity.ok(userDetailResponseDto);
    }

    @PutMapping("/edit/{userid}")
    public ResponseEntity<?> edit(@RequestBody UserDetailRequestDto dto, @PathVariable("userid") Long userid) {
        UserDetailResponseDto userDetailResponseDto = userService.editUser(dto, userid);
        return ResponseEntity.ok(userDetailResponseDto);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        UserDetailResponseDto userDetailResponseDto = userService.showUserDetail(id);
        return ResponseEntity.ok(userDetailResponseDto);
    }
}
