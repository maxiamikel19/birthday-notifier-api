package com.maxiamikel.BirthdayNotifierAPI.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {
    private Integer statusCode;
    private String message;

}
