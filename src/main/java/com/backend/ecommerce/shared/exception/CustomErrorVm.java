package com.backend.ecommerce.shared.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
public class CustomErrorVm {
    private final String message;
    private final int statusCode;
    private final LocalDateTime timestamp;

    public CustomErrorVm(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now(ZoneOffset.UTC);
    }
}
