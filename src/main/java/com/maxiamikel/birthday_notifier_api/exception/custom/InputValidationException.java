package com.maxiamikel.birthday_notifier_api.exception.custom;

public class InputValidationException extends RuntimeException {
    public InputValidationException(String message) {
        super(message);
    }

}
