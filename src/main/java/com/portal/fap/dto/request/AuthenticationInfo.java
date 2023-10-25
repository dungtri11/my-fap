package com.portal.fap.dto.request;

import com.portal.fap.common.Status;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationInfo {
    private String credential;
    private String password;
}
