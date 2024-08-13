package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.user.UserCreateDto;
import com.backend.ecommerce.dtos.user.UserDto;
import com.backend.ecommerce.dtos.user.UserLoginDto;
import com.backend.ecommerce.dtos.user.UserLoginResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserLoginResponseDto loginUser(UserLoginDto userLoginDto);

    void logoutUser();

    UserDto register(UserCreateDto userCreateDto);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    Optional<UserDto> getUserById(UUID id);

    UserDto deleteUser(UUID id);
}
