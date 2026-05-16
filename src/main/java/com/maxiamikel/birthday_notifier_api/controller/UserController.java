package com.maxiamikel.birthday_notifier_api.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxiamikel.birthday_notifier_api.dto.request.CreateUserRequest;
import com.maxiamikel.birthday_notifier_api.dto.request.UpdateUserRequest;
import com.maxiamikel.birthday_notifier_api.dto.response.UserResponse;
import com.maxiamikel.birthday_notifier_api.service.interfaces.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(
            @PageableDefault(size = 20, page = 0, sort = "createdAt") Pageable pageable) {

        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {

        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id, @RequestBody @Valid UpdateUserRequest request) {

        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
