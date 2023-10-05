package com.portal.fap.dto.response;

import com.portal.fap.common.Gender;
import com.portal.fap.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponseDto {
    private String username;
    private String email;
    private String name;
    private Gender gender;
    private String phones;
    private String address;
    private String image;

    public UserDetailResponseDto(User user) {
        this.username = user.getAccount().getUsername();
        this.email = user.getAccount().getEmail();
        this.name = user.getInformation().getFullName();
        this.gender = user.getInformation().getGender();
        this.phones = user.getPhones();
        this.address = user.getInformation().getAddress();
    }
}
