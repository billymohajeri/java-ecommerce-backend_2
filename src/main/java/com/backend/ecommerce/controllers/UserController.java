package com.backend.ecommerce.controllers;

import com.backend.ecommerce.dtos.user.UserDto;
import com.backend.ecommerce.dtos.user.UserLoginDto;
import com.backend.ecommerce.services.UserServiceImpl;
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
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public void loginUser(@Valid @RequestBody UserLoginDto userLoginDTO) {
        userServiceImpl.loginUser(userLoginDTO);
    }

    @PostMapping("/logout")
    public void loginUser() {
        userServiceImpl.logoutUser();
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userServiceImpl.getAllUsers();
        GlobalResponse<List<UserDto>> response = new GlobalResponse<>(users, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<UserDto>> getUserById(@PathVariable UUID id) {
        Optional<UserDto> userDto = userServiceImpl.getUserById(id);
        return userDto.map(dto -> ResponseEntity.ok(new GlobalResponse<>(dto, null))).orElseThrow();
    }

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<UserDto>> registerUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(new GlobalResponse<>(userServiceImpl.register(userDto), null));
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
}
