package com.portal.fap.controller;

import com.portal.fap.dto.request.UserDetailDto;
import com.portal.fap.dto.request.UserJsonRequestDto;
import com.portal.fap.dto.response.UserDetailResponseDto;
import com.portal.fap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestPart("jsonData") UserJsonRequestDto json,
                                 @RequestPart(name = "file", required = false) MultipartFile file) throws IOException {
        UserDetailDto dto = new UserDetailDto(json.getInformation(), json.getPhones(), file);
        UserDetailResponseDto userDetailResponseDto = userService.addUser(dto);
        return ResponseEntity.ok(userDetailResponseDto);
    }

    @PutMapping("/edit/{userid}")
    public ResponseEntity<?> edit(@RequestPart(name = "jsonData", required = false) UserJsonRequestDto json,
                                  @RequestPart(name = "file", required = false) MultipartFile file,
                                  @PathVariable("userid") Long userid) throws IOException {
        UserDetailDto dto = new UserDetailDto(json.getInformation(), json.getPhones(), file);
        UserDetailResponseDto userDetailResponseDto = userService.editUser(dto, userid);
        return ResponseEntity.ok(userDetailResponseDto);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        UserDetailResponseDto userDetailResponseDto = userService.showUserDetail(id);
        return ResponseEntity.ok(userDetailResponseDto);
    }
}
