package com.maxiamikel.birthday_notifier_api.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maxiamikel.birthday_notifier_api.dto.request.CreateUserRequest;
import com.maxiamikel.birthday_notifier_api.dto.request.UpdateUserRequest;
import com.maxiamikel.birthday_notifier_api.dto.response.UserResponse;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    Page<UserResponse> findAll(Pageable pageable);

    UserResponse findById(UUID id);

    UserResponse update(UUID id, UpdateUserRequest request);

    void delete(UUID id);

    Page<UserResponse> findBirthdaysByMonth(int month, Pageable pageable);
}
