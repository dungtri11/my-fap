package com.portal.fap.dto.request;

import com.portal.fap.entity.CIInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {
    private CIInformation information;
    private String phones;
    private MultipartFile imageFile;
}
