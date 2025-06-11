package com.maxiamikel.BirthdayNotifierAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxiamikel.BirthdayNotifierAPI.dtos.UserDto;
import com.maxiamikel.BirthdayNotifierAPI.entities.User;
import com.maxiamikel.BirthdayNotifierAPI.mapper.UserMapper;
import com.maxiamikel.BirthdayNotifierAPI.payload.LocalApiResponse;
import com.maxiamikel.BirthdayNotifierAPI.services.birthday.BirthdayService;

@RestController
@RequestMapping("/api/v1/birthdays")
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

    private final String SUCCESS_MESSAGE = "SUCCESS";

    @GetMapping("/birthdays-month-current")
    public ResponseEntity<LocalApiResponse> getAnniversariesOfCurrentMonth() {
        List<User> users = birthdayService.getAnniversariesOfCurrentMonth();
        List<UserDto> usersDto = users.stream().map(user -> UserMapper.mapToDto(user)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));
    }

    @GetMapping("/birthdays/next-month")
    public ResponseEntity<LocalApiResponse> getAnniversariesNextMonth() {
        List<User> users = birthdayService.getAnniversariesNextMonth();
        List<UserDto> usersDto = users.stream().map(user -> UserMapper.mapToDto(user)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));
    }

    @GetMapping("/last-birthdays")
    public ResponseEntity<LocalApiResponse> getAnniversariesLastMonth() {
        List<User> users = birthdayService.getAnniversariesLastMonth();
        List<UserDto> usersDto = users.stream().map(user -> UserMapper.mapToDto(user)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));
    }

    @GetMapping("/birthdays/custom")
    public ResponseEntity<LocalApiResponse> getAnniversariesCustomMonth(@RequestParam(defaultValue = "1") int month) {
        List<User> users = birthdayService.getAnniversariesCustomMonth(month);
        List<UserDto> usersDto = users.stream().map(user -> UserMapper.mapToDto(user)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));
    }
}
