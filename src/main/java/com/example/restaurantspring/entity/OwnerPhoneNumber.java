package com.example.restaurantspring.entity;

import com.example.restaurant.exception.InvalidPhoneNumberException;
import com.example.restaurant.util.PhoneNumberUtil;

public interface OwnerPhoneNumber {

    default String getPhoneNumberFrom(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        if (PhoneNumberUtil.isValid(phoneNumber)) {
            return phoneNumber;
        } else {
            throw new InvalidPhoneNumberException();
        }
    }
}
