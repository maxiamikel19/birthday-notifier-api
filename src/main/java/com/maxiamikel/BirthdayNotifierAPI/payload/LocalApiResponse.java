package com.maxiamikel.BirthdayNotifierAPI.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalApiResponse {
    private String status;
    private String message;
    private Object data;
}
