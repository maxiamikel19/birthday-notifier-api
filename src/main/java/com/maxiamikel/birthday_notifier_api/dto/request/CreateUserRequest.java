package com.maxiamikel.birthday_notifier_api.dto.request;

import java.time.LocalDate;

import com.maxiamikel.birthday_notifier_api.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequest(

        @NotBlank(message = "First name is required") String firstName,

        @NotBlank(message = "Last name is required") String lastName,

        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,

        @NotBlank(message = "Phone number is required") @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Invalid phone number format") String phoneNumber,

        @NotNull(message = "Birth date is required") @Past(message = "Birth date must be in the past") LocalDate birthDate,

        @NotNull(message = "Gender is required") Gender gender,

        @NotNull(message = "Affiliation date is required") @PastOrPresent(message = "Affiliation date cannot be in the future") LocalDate affiliationDate) {
}