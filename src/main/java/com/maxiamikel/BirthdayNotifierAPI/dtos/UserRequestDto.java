package com.maxiamikel.BirthdayNotifierAPI.dtos;

import java.time.LocalDate;

import com.maxiamikel.BirthdayNotifierAPI.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequestDto {
    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(message = "Birthday is required")
    private LocalDate birthday;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
