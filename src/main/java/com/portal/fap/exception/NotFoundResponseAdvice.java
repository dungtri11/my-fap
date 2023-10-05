package com.portal.fap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class NotFoundResponseAdvice {
    @ResponseBody
    @ExceptionHandler(NotFoundResponseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandle(NotFoundResponseException exception) {
        Map<String, String> error = new HashMap<>();
        String[] params = exception.getMessage().split(";");
        for (String param : params) {
            param = param.trim();
            if (param.length() == 0) continue;
            String[] err = param.split(":");
            error.put(err[0].trim(), err[1].trim());
        }
        return error;
    }
}
