package com.maxiamikel.BirthdayNotifierAPI.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    M("Male"),
    F("Female");

    private String gender;
}
