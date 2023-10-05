package com.portal.fap.validation;

import com.portal.fap.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidAccount {
    public static List<String> getAccountInvalids(Account account) {
        return Stream.concat(Stream.concat(
                getUsernameInvalids(account.getUsername()).stream(),
                getEmailInvalids(account.getEmail()).stream()),
                getPasswordInvalids(account.getPassword()).stream())
                .collect(Collectors.toList());
    }

    private static List<String> getUsernameInvalids(String username) {
        List<String> invalids = new ArrayList<>();
        if (!Pattern.compile(".{8,64}").matcher(username).find()) {
            invalids.add("101 : Username must more than 8 and less than 64 characters");
        }
        if (!Pattern.compile("[a-zA-Z0-9]").matcher(username).find()) {
            invalids.add("102 : Username can contain only letter and number characters");
        }
        return invalids;
    }

    private static List<String> getPasswordInvalids(String password) {
        List<String> invalids = new ArrayList<>();
        if (!Pattern.compile(".{9,128}").matcher(password).find()) {
            invalids.add("103 : Password must more than 9 and less than 64 characters");
        }
        if (!Pattern.compile("[0-9]+").matcher(password).find()) {
            invalids.add("104 : Password must contain at least 1 number character");
        }
        if (!Pattern.compile("[A-Z]+").matcher(password).find()) {
            invalids.add("105 : Password must contain at least 1 capital character");
        }
        if (!Pattern.compile("[^A-Z0-9]+").matcher(password).find()) {
            invalids.add("106 : Password must contain at least 1 special character");
        }
        return invalids;
    }

    private static List<String> getEmailInvalids(String email) {
        List<String> invalids = new ArrayList<>();
        if (!Pattern.compile("^[a-zA-Z0-9]+@").matcher(email).find()) {
            invalids.add("107 : Email name can contain only letter and number characters");
        }
        if (!Pattern.compile("(.*@fpt\\.edu\\.vn$)|(.*@fe\\.edu\\.vn)|(.*@gmail\\.com)").matcher(email).find()) {
            invalids.add("108 : Email must be FPT or Gmail domain");
        }
        return invalids;
    }

}
