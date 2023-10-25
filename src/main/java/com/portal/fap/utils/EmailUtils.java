package com.portal.fap.utils;

public class EmailUtils {
    public static String generateEmailFromName(String name, String roleNumber) {
        String[] parts = name.split(" ");
        String last = parts[parts.length-1];
        String prefix = last.toLowerCase();

        for (String part : parts) {
            part = part.trim();
            if (part.length() == 0 || part.equals(last)) continue;;
            prefix += part.charAt(0);
        }
        return prefix.toLowerCase() + roleNumber.toLowerCase() +"@fpt.edu.vn";
    }
}
