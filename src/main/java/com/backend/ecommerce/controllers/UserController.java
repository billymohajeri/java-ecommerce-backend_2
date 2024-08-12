package com.backend.ecommerce.controllers;

import com.backend.ecommerce.services.dtos.UserDto;
import com.backend.ecommerce.services.dtos.UserLoginDto;
import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.services.UserService;
import com.backend.ecommerce.shared.exception.CustomErrorVm;
import com.backend.ecommerce.shared.response.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public void loginUser(@Valid @RequestBody UserLoginDto userLoginDTO) {
        userService.loginUser(userLoginDTO);
    }

    @PostMapping("/logout")
    public void loginUser() {
        userService.logoutUser();
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        GlobalResponse<List<UserDto>> response = new GlobalResponse<>(users, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<UserDto>> getUserById(@PathVariable UUID id) {
        UserDto user = userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
        return ResponseEntity.ok(new GlobalResponse<>(user, null));
    }

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<User>> registerUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(new GlobalResponse<>(userService.register(userDto), null));
    }

    @PutMapping
    public ResponseEntity<GlobalResponse<User>> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(new GlobalResponse<>(userService.updateUser(userDto), null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<User>> deleteUser(@PathVariable UUID id) {
        GlobalResponse<User> response = new GlobalResponse<>(userService.deleteUser(id), null);
        return ResponseEntity.ok(response);
    }
}
