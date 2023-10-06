package com.portal.fap.dto.request;

import com.portal.fap.entity.CIInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserJsonRequestDto {
    private CIInformation information;
    private String phones;
}
