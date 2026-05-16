package com.maxiamikel.birthday_notifier_api.exception.custom;

public record StandardError(
        Integer codeStatus,
        Long timestemp,
        Object message) {

}
