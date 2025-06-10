package com.maxiamikel.BirthdayNotifierAPI.mapper;

import com.maxiamikel.BirthdayNotifierAPI.dtos.UserDto;
import com.maxiamikel.BirthdayNotifierAPI.entities.User;

public class UserMapper {
    public static UserDto mapToDto(User entity) {
        return UserDto.builder()
                .userId(entity.getUserId())
                .name(entity.getName())
                .birthday(entity.getBirthday())
                .gender(entity.getGender())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }
}
