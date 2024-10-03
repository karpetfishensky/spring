package com.example.restaurantspring.util;

import java.util.regex.Pattern;

public class PhoneNumberUtil implements Validation {

    private static final String PHONE_NUMBER_REGEX = "^\\+?[0-9]{11}$";
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

    public static boolean isValid(Object object) {
        String phoneNumber = (String) object;
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }
        return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }

}
