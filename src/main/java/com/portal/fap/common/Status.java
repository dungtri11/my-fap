package com.portal.fap.common;

public enum Status {
    FAILED_TO_AUTHENTICATE(100001, "Authentication information isn't correct"),
    AUTHENTICATION_SUCCESSFUL(100002, "Successfully authenticated")
    ;

    public long code;
    public String message;

    Status(long code, String message) {
        this.code = code;
        this.message = message;
    }
}
