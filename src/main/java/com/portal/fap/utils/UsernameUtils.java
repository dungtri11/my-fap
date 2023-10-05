package com.portal.fap.utils;

public class UsernameUtils {
    public static String generateUsernameFromName(String name) {
        return EmailUtils.generateEmailFromName(name).replaceAll("@fpt\\.edu\\.vn", "");
    }
}
