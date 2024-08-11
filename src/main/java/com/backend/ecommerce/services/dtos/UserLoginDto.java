package com.backend.ecommerce.services.dtos;

import lombok.Getter;

public record UserLoginDto(String email,
                           String password) {
}
