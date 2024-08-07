package com.backend.ecommerce.services.dtos;

public record UserDto(String id, String firstName, String lastName, String address, String email, String password,
                      String phoneNumber, String birthDate, String role) {

}
