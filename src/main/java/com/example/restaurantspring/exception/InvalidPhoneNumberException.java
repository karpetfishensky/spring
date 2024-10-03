package com.example.restaurantspring.exception;

public class InvalidPhoneNumberException extends IllegalArgumentException{

    public InvalidPhoneNumberException() {
        super("Номер телефона был введён неправильно. Правильно: +79998887654 или 89993332210.");
    }

    public InvalidPhoneNumberException(String s) {
        super(s);
    }
}
