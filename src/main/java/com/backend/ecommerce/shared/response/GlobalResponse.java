package com.backend.ecommerce.shared.response;

import com.backend.ecommerce.shared.exception.CustomErrorVm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public record GlobalResponse<T>(
        T data,
        CustomErrorVm error,
        LocalDateTime timestamp
) {
    public GlobalResponse(T data, CustomErrorVm error) {
        this(data, error, LocalDateTime.now(ZoneOffset.UTC));
    }
}