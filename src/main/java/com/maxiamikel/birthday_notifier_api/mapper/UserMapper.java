package com.maxiamikel.birthday_notifier_api.mapper;

import org.springframework.stereotype.Component;

import com.maxiamikel.birthday_notifier_api.dto.request.CreateUserRequest;
import com.maxiamikel.birthday_notifier_api.dto.request.UpdateUserRequest;
import com.maxiamikel.birthday_notifier_api.dto.response.UserResponse;
import com.maxiamikel.birthday_notifier_api.entity.User;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getBirthDate(),
                user.getGender().name(),
                user.getAffiliationDate(),
                user.getAge(),
                user.getAffiliationYears(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public User toEntity(CreateUserRequest request) {
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .gender(request.gender())
                .birthDate(request.birthDate())
                .affiliationDate(request.affiliationDate())
                .build();
    }

    public void updateEntityFromRequest(UpdateUserRequest request, User user) {

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phoneNumber());
        user.setBirthDate(user.getBirthDate());
        user.setAffiliationDate(user.getAffiliationDate());
        user.setGender(user.getGender());
    }

}
