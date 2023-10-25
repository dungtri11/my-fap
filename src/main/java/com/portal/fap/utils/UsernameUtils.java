package com.portal.fap.utils;

public class UsernameUtils {
    public static String generateUsernameFromNameAndRoleNumber(String name, String roleNumber) {
        return EmailUtils.generateEmailFromName(name, roleNumber).replaceAll("@fpt\\.edu\\.vn", "");
    }
}
