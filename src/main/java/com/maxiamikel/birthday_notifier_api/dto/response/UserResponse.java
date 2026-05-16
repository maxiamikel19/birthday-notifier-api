package com.maxiamikel.birthday_notifier_api.dto.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String fullName,
        String email,
        String phoneNumber,
        LocalDate birthDate,
        String gender,
        LocalDate affiliationDate,
        int age,
        int affiliationYears,
        Instant createdAt,
        Instant updatedAt) {

}
