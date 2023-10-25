package com.portal.fap.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationInfo {
    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$",
            message = "username should have 8-20 character, can contain words, numbers and '.' or '_', " +
                    "no contain '_' or '.' at the beginning or the end, and strings '..', '._', '_.' or '__'")
    private String username;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email isn't in correct format")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "password must contain minimum eight characters, at least one uppercase letter," +
                    " one lowercase letter, one number and one special character")
    private String password;
}
