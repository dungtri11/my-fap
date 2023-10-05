package com.portal.fap.dto.request;

import com.portal.fap.common.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailRequestDto {
    private String name;
    private String idCard;
    private Gender gender;
    private String phones;
    private String address;
}
