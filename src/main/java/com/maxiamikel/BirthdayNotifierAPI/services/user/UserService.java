package com.maxiamikel.BirthdayNotifierAPI.services.user;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maxiamikel.BirthdayNotifierAPI.dtos.UserRequestDto;
import com.maxiamikel.BirthdayNotifierAPI.dtos.UserUpdateRequestDto;
import com.maxiamikel.BirthdayNotifierAPI.entities.User;

public interface UserService {
    User create(UserRequestDto request);

    Page<User> findAll(Pageable pageable);

    User findById(String userId);

    void delete(String userId);

    User update(String userId, UserUpdateRequestDto request);

    List<User> findByBirthdayToday(LocalDate searchDate);
}
