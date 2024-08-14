package com.backend.ecommerce.dtos.user;

public record UserLoginResponseDto(String token, UserDto user) {
}
