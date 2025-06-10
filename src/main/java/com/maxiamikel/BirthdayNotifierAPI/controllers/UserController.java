package com.maxiamikel.BirthdayNotifierAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        var pageUsers = userService.findAll(pageable);
        var pageUsersDto = pageUsers.map(user -> UserMapper.mapToDto(user));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(SUCCESS_MESSAGE, "Found", pageUsersDto));
    }

}
