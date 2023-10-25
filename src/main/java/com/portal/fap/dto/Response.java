package com.portal.fap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portal.fap.common.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private Status status;
    private T body;
}
