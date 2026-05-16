package com.maxiamikel.birthday_notifier_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateUserRequest(

        @NotBlank(message = "First name is required") String firstName,

        @NotBlank(message = "Last name is required") String lastName,

        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,

        @NotBlank(message = "Phone number is required") @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Invalid phone number format") String phoneNumber) {

}
