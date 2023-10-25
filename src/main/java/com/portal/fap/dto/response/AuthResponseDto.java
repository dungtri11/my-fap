package com.portal.fap.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor(staticName = "construct")
@NoArgsConstructor
public class AuthResponseDto {
    private String token;
    private Date expireTime;
}
