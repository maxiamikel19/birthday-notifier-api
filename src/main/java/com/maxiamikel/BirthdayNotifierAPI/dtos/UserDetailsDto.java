package com.maxiamikel.BirthdayNotifierAPI.dtos;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import com.maxiamikel.BirthdayNotifierAPI.enums.Gender;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDto {
    private String userId;

    private String name;

    private LocalDate birthday;

    private Gender gender;

    private String phone;

    private String email;

    private LocalDate registerDate;

    public Integer getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public long getDaysUntilNextBirthday() {
        LocalDate today = LocalDate.now();
        LocalDate nextDate = MonthDay.from(birthday).atYear(today.getYear());

        if (!nextDate.isAfter(today)) {
            nextDate = nextDate.plusYears(1);
        }

        return ChronoUnit.DAYS.between(today, nextDate);
    }

}
