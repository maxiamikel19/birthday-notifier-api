package com.maxiamikel.birthday_notifier_api.exception.custom;

public class ResourceDuplicatedException extends RuntimeException {
    public ResourceDuplicatedException(String message) {
        super(message);
    }

}
