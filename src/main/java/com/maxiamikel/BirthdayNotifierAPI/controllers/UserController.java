package com.maxiamikel.BirthdayNotifierAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxiamikel.BirthdayNotifierAPI.dtos.UserRequestDto;
import com.maxiamikel.BirthdayNotifierAPI.dtos.UserUpdateRequestDto;
import com.maxiamikel.BirthdayNotifierAPI.mapper.UserMapper;
import com.maxiamikel.BirthdayNotifierAPI.payload.LocalApiResponse;
import com.maxiamikel.BirthdayNotifierAPI.services.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User controller", description = "Basic CRUD for user entity")
public class UserController {

    @Autowired
    private UserService userService;

    private final String SUCCESS_MESSAGE = "SUCCESS";

    @PostMapping("/save")
    @Operation(summary = "Create a new user account")
    @ApiResponse(responseCode = "201", description = "Successfully created")
    @ApiResponse(responseCode = "40o", description = "Bad request, file is required")
    @ApiResponse(responseCode = "406", description = "Fatal error! Please verify the Gender[M or F is available] value or the Date value[yyyy-MM-dd is available]\"")
    @ApiResponse(responseCode = "409", description = "User aleready exists")
    public ResponseEntity<LocalApiResponse> create(@RequestBody @Valid UserRequestDto request) {

        var userDto = UserMapper.mapToDto(userService.create(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new LocalApiResponse(SUCCESS_MESSAGE, HttpStatus.CREATED.getReasonPhrase(), userDto));
    }

    @GetMapping("/all")
    @Operation(summary = "List page(s) of registred users")
    @ApiResponse(responseCode = "200", description = "Successfully returned page(s) of users")
    public ResponseEntity<LocalApiResponse> findAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        var pageUsers = userService.findAll(pageable);
        var pageUsersDto = pageUsers.map(user -> UserMapper.mapToDto(user));
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", pageUsersDto));
    }

    @GetMapping("/find/{userId}")
    @Operation(summary = "Show a specific user details by id")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User ID not found")
    public ResponseEntity<LocalApiResponse> findById(@PathVariable String userId) {
        var user = userService.findById(userId);
        var usersDto = UserMapper.mapToDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));

    }

    @PutMapping("/{userId}/update")
    @Operation(summary = "Update a user data")
    @ApiResponse(responseCode = "200", description = "Successfully updated")
    @ApiResponse(responseCode = "40o", description = "Bad request, file is required")
    @ApiResponse(responseCode = "404", description = "User ID not found")
    @ApiResponse(responseCode = "500", description = "Email aleready used by annother user")
    public ResponseEntity<LocalApiResponse> update(@PathVariable String userId,
            @RequestBody @Valid UserUpdateRequestDto request) {
        var userDto = userService.update(userId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new LocalApiResponse(SUCCESS_MESSAGE, HttpStatus.OK.getReasonPhrase(), userDto));
    }

    @DeleteMapping("/{userId}/delete")
    @Operation(summary = "To delete a user in the database")
    @ApiResponse(responseCode = "200", description = "Successfully updated")
    @ApiResponse(responseCode = "404", description = "User ID not found")
    public ResponseEntity<LocalApiResponse> delete(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new LocalApiResponse(SUCCESS_MESSAGE, "Successfully deleted", null));

    }

}
