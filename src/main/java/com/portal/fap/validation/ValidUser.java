package com.portal.fap.validation;

import com.portal.fap.common.Gender;
import com.portal.fap.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidUser {
    public static List<String> getUserInvalids(User user) {
        return Stream.concat(
                Stream.concat(
                        Stream.concat(
                                Stream.concat(
                                        getNameInvalids(user.getInformation().getFullName()).stream(),
                                        getIdCardInvalids(user.getInformation().getIdCard()).stream()
                                ),
                                getGenderInvalids(user.getInformation().getGender()).stream()
                        ),
                        getPhoneInvalids(user.getPhones()).stream()
                ),
                getAddressInvalids(user.getInformation().getAddress()).stream()
        ).collect(Collectors.toList());
    }

    public static List<String> getNameInvalids(String name) {
        List<String> invalids = new ArrayList<>();
        if (!Pattern.compile("^[a-zA-Z ]+$").matcher(name).find()) {
            invalids.add("201 : Name can contain only letter characters and white space");
        }
        if (!Pattern.compile("^.{0,64}$").matcher(name).find()) {
            invalids.add("202 : Name should contain less than 64 character");
        }
        if (!Pattern.compile("[a-zA-Z] [a-zA-Z]").matcher(name).find()) {
            invalids.add("203 : Name should contain first name and last name separate by 1 white space");
        }
        return invalids;
    }

    public static List<String> getIdCardInvalids(String idCard) {
        List<String> invalids = new ArrayList<>();
        if (!Pattern.compile("^[0-9]{12}$").matcher(idCard).find()) {
            invalids.add("204 : Id card should have exact 12 number character");
        }
        return invalids;
    }

    public static List<String> getGenderInvalids(Gender gender) {
        List<String> invalids = new ArrayList<>();
        if (!Pattern.compile("(^MALE$)|(^FEMALE$)|(^OTHER$)").matcher(gender.toString()).find()) {
            invalids.add("205 : Gender can only be 'MALE', 'FEMALE' or 'OTHER'");
        }
        return invalids;
    }

    private static List<String> getPhoneInvalids(String phone) {
        List<String> invalids = new ArrayList<>();
        if (!Pattern.compile("^[0-9+]+$").matcher(phone).find()) {
            invalids.add("206 : Phone can contain only number and '+' characters");
        }
        if (!Pattern.compile("(^\\+84)|(^0)").matcher(phone).find()) {
            invalids.add("207 : Phone should start with '+84' or '0'");
        }
        if (!Pattern.compile("((^\\+84)|(^0))[0-9]{9}$").matcher(phone).find()) {
            invalids.add("208 : Phone should have 9 postfix number characters");
        }
        return invalids;
    }

    public static List<String> getAddressInvalids(String name) {
        List<String> invalids = new ArrayList<>();
        if (!Pattern.compile("^[a-zA-Z0-9, ]+$").matcher(name).find()) {
            invalids.add("209 : Address can contain only letter, number characters, comma and white space");
        }
        if (!Pattern.compile("^.{0,64}$").matcher(name).find()) {
            invalids.add("210 : Address should contain less than 64 character");
        }
        if (!Pattern.compile("^[^,]+, [^,]+, [^,]+$").matcher(name).find()) {
            invalids.add("211 : Address should contain commune, district and province");
        }
        return invalids;
    }
}
