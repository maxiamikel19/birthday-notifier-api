package com.maxiamikel.BirthdayNotifierAPI.controllers;

import java.time.LocalDate;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/birthdays")
@Tag(name = "Birthday resource", description = "Manage the way to explore the users birthday infos")
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

    private final String SUCCESS_MESSAGE = "SUCCESS";

    @GetMapping("/birthdays-month-current")
    @Operation(summary = "Return all birthdays in the current month")
    @ApiResponse(responseCode = "200", description = "Found")
    @ApiResponse(responseCode = "204", description = "No birthday to celebrate in this month")
    public ResponseEntity<LocalApiResponse> getAnniversariesOfCurrentMonth() {
        List<User> users = birthdayService.getAnniversariesOfCurrentMonth();
        List<UserDto> usersDto = users.stream().map(user -> UserMapper.mapToDto(user)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));
    }

    @GetMapping("/birthdays/next-month")
    @Operation(summary = "Return all birthdays in the next month from the current")
    @ApiResponse(responseCode = "200", description = "Found")
    @ApiResponse(responseCode = "204", description = "No birthday to celebrate in this month")
    public ResponseEntity<LocalApiResponse> getAnniversariesNextMonth() {
        List<User> users = birthdayService.getAnniversariesNextMonth();
        List<UserDto> usersDto = users.stream().map(user -> UserMapper.mapToDto(user)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));
    }

    @GetMapping("/last-birthdays")
    @Operation(summary = "Return all birthdays in the last month from the current")
    @ApiResponse(responseCode = "200", description = "Found")
    @ApiResponse(responseCode = "204", description = "No birthday to celebrate in this month")
    public ResponseEntity<LocalApiResponse> getAnniversariesLastMonth() {
        List<User> users = birthdayService.getAnniversariesLastMonth();
        List<UserDto> usersDto = users.stream().map(user -> UserMapper.mapToDto(user)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));
    }

    @GetMapping("/birthdays/custom")
    @Operation(summary = "Return all birthdays in the a specific month")
    @ApiResponse(responseCode = "200", description = "Found")
    @ApiResponse(responseCode = "204", description = "No birthday to celebrate in this month")
    @ApiResponse(responseCode = "500", description = "Invalid month value. Use a number between 1 and 12")
    public ResponseEntity<LocalApiResponse> getAnniversariesCustomMonth(@RequestParam(defaultValue = "1") int month) {
        List<User> users = birthdayService.getAnniversariesCustomMonth(month);
        List<UserDto> usersDto = users.stream().map(user -> UserMapper.mapToDto(user)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));
    }

    @GetMapping
    @Operation(summary = "Return all birthdays today")
    @ApiResponse(responseCode = "200", description = "Found")
    @ApiResponse(responseCode = "204", description = "No birthday to celebrate in this month")
    public ResponseEntity<LocalApiResponse> findByBirthdayToday(@RequestParam(defaultValue = "1") int month) {
        List<User> users = birthdayService.findByBirthdayToday(LocalDate.now());
        List<UserDto> usersDto = users.stream().map(user -> UserMapper.mapToDto(user)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new LocalApiResponse(SUCCESS_MESSAGE, "Found", usersDto));
    }
}
