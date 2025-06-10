package com.maxiamikel.BirthdayNotifierAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxiamikel.BirthdayNotifierAPI.dtos.UserRequestDto;
import com.maxiamikel.BirthdayNotifierAPI.mapper.UserMapper;
import com.maxiamikel.BirthdayNotifierAPI.payload.ApiResponse;
import com.maxiamikel.BirthdayNotifierAPI.services.user.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final String SUCCESS_MESSAGE = "SUCCESS";

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid UserRequestDto request) {

        var userDto = UserMapper.mapToDto(userService.create(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(SUCCESS_MESSAGE, HttpStatus.CREATED.getReasonPhrase(), userDto));
    }

}
