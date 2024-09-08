package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.user.*;

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

  Optional<UserDto> getUserProfile();

  UserDto patchUserName(UUID id, String firstName, String lastName);

  UserDto updateUserName(UUID id, UserNameEditDto userNameEditDto);
}
