package com.maxiamikel.birthday_notifier_api.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maxiamikel.birthday_notifier_api.dto.request.CreateUserRequest;
import com.maxiamikel.birthday_notifier_api.dto.request.UpdateUserRequest;
import com.maxiamikel.birthday_notifier_api.dto.response.UserResponse;
import com.maxiamikel.birthday_notifier_api.entity.User;
import com.maxiamikel.birthday_notifier_api.exception.custom.InputValidationException;
import com.maxiamikel.birthday_notifier_api.exception.custom.ResourceDuplicatedException;
import com.maxiamikel.birthday_notifier_api.exception.custom.ResourceNotFoundException;
import com.maxiamikel.birthday_notifier_api.mapper.UserMapper;
import com.maxiamikel.birthday_notifier_api.repository.UserRepository;
import com.maxiamikel.birthday_notifier_api.service.interfaces.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponse create(CreateUserRequest request) {

        validateEmailAvailability(request.email());
        validatePhoneNumber(request.phoneNumber());
        validateAffiliationDate(request.birthDate(), request.affiliationDate());

        User user = userMapper.toEntity(request);

        log.info("Creating new user as: {}", user.getEmail());
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        if (pageable.getPageSize() > 100) {
            throw new InputValidationException("Maximum page size allowed is 100");
        }

        return userRepository.findAll(pageable).map(userMapper::toResponse);
    }

    @Override
    public UserResponse findById(UUID id) {
        log.info("Fetching  user: {}", id);
        return userMapper.toResponse(getUserOrThrow(id));
    }

    @Override
    @Transactional
    public UserResponse update(UUID id, UpdateUserRequest request) {

        User user = getUserOrThrow(id);

        if (!user.getEmail().equals(request.email())) {
            validateEmailAvailability(request.email());
        }

        userMapper.updateEntityFromRequest(request, user);

        log.info("Updating user: {}", id);

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(UUID id) {

        User user = getUserOrThrow(id);

        log.info("Deleting  user: {}", id);
        userRepository.delete(user);
    }

    @Override
    public Page<UserResponse> findBirthdaysByMonth(int month, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBirthdaysByMonth'");
    }

    private void validateEmailAvailability(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ResourceDuplicatedException("Email already registered");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.startsWith("+")) {
            throw new InputValidationException("Phone number should not start with '+'");
        }

    }

    private void validateAffiliationDate(LocalDate birthDate, LocalDate affiliationDate) {
        if (affiliationDate.isBefore(birthDate)) {
            throw new InputValidationException("Affiliation date cannot be before birth date");
        }
    }

    private User getUserOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

}
