package com.backend.ecommerce.controllers;

import com.backend.ecommerce.services.dtos.UserDto;
import com.backend.ecommerce.services.dtos.UserLoginDto;
import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.services.UserService;
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
    private UserService userService;

    @PostMapping("/login")
    public void loginUser(@RequestBody UserLoginDto userLoginDTO) {
        userService.loginUser(userLoginDTO);
    }

    @PostMapping("/logout")
    public void loginUser() {
        userService.logoutUser();
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        Optional<UserDto> user = userService.getUserById(UUID.fromString(id));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        //TODO: validate UUID
        return ResponseEntity.ok(userService.deleteUser(UUID.fromString(id)));
    }
}
