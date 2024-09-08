package com.backend.ecommerce.dtos.user;


import jakarta.validation.constraints.NotBlank;

public record UserNameEditDto(
        @NotBlank String firstName,
        @NotBlank String lastName) {
}
