package com.maxiamikel.BirthdayNotifierAPI.dtos;

import java.time.LocalDate;

import com.maxiamikel.BirthdayNotifierAPI.enums.Gender;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String userId;

    private String name;

    private LocalDate birthday;

    private Gender gender;

    private String phone;

    private String email;
}
