package com.backend.ecommerce.controllers;

import com.backend.ecommerce.dtos.user.*;
import com.backend.ecommerce.services.interfaces.UserService;
import com.backend.ecommerce.shared.response.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  @Autowired
  private UserService userServiceImpl;

  @PostMapping("/login")
  public ResponseEntity<GlobalResponse<UserLoginResponseDto>> loginUser(@Valid @RequestBody UserLoginDto userLoginDTO) {
    return ResponseEntity.ok(new GlobalResponse<>(userServiceImpl.loginUser(userLoginDTO), null));
  }

  @PostMapping("/logout")
  public void logoutUser() {
    userServiceImpl.logoutUser();
  }

  @GetMapping
  public ResponseEntity<GlobalResponse<List<UserDto>>> getAllUsers() {
    List<UserDto> users = userServiceImpl.getAllUsers();
    GlobalResponse<List<UserDto>> response = new GlobalResponse<>(users, null);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/profile")
  public ResponseEntity<GlobalResponse<UserDto>> getUserProfile() {
    Optional<UserDto> userDto = userServiceImpl.getUserProfile();
    return userDto.map(dto -> ResponseEntity.ok(new GlobalResponse<>(dto, null))).orElseThrow();
  }

  @GetMapping("/{id}")
  public ResponseEntity<GlobalResponse<UserDto>> getUserById(@PathVariable UUID id) {
    Optional<UserDto> userDto = userServiceImpl.getUserById(id);
    return userDto.map(dto -> ResponseEntity.ok(new GlobalResponse<>(dto, null))).orElseThrow();
  }

  @PostMapping("/register")
  public ResponseEntity<GlobalResponse<UserDto>> registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
    return ResponseEntity.ok(new GlobalResponse<>(userServiceImpl.register(userCreateDto), null));
  }

  @PutMapping
  public ResponseEntity<GlobalResponse<UserDto>> updateUser(@Valid @RequestBody UserDto userDto) {
    return ResponseEntity.ok(new GlobalResponse<>(userServiceImpl.updateUser(userDto), null));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GlobalResponse<UserDto>> deleteUser(@PathVariable UUID id) {
    GlobalResponse<UserDto> response = new GlobalResponse<>(userServiceImpl.deleteUser(id), null);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GlobalResponse<UserDto>> patchUserName(@PathVariable UUID id, @RequestBody String firstName, @RequestBody String lastName) {
    GlobalResponse<UserDto> response = new GlobalResponse<>(userServiceImpl.patchUserName(id, firstName, lastName), null);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GlobalResponse<UserDto>> updateUserName(@PathVariable UUID id, @RequestBody UserNameEditDto userNameEditDto) {
    return ResponseEntity.ok(new GlobalResponse<>(userServiceImpl.updateUserName(id, userNameEditDto), null));
  }


}

//
//@PatchMapping("/{id}")
//public ResponseEntity<GlobalResponse<ProductDto>> patchProductStock(@PathVariable UUID id, @RequestBody int stock) {
//  GlobalResponse<ProductDto> response = new GlobalResponse<>(productService.patchProductStock(id, stock), null);
//  return ResponseEntity.ok(response);
//}