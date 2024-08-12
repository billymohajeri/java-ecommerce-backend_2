package com.backend.ecommerce.services.dtos;

import com.backend.ecommerce.entities.enums.AuthenticationRole;

import java.util.UUID;

public record UserDto(UUID id, String firstName, String lastName, String address, String email, String password,
                      String phoneNumber, String birthDate, AuthenticationRole role) {

}
