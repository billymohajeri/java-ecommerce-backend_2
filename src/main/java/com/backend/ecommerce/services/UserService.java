package com.backend.ecommerce.services;

import com.backend.ecommerce.services.dtos.UserDto;
import com.backend.ecommerce.services.dtos.UserLoginDto;
import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.repositories.UserJpaRepo;
import com.backend.ecommerce.services.mappers.UserMapper;
import com.backend.ecommerce.shared.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserJpaRepo userJpaRepo;
    @Autowired
    UserMapper userMapper;

    public void loginUser(UserLoginDto userLoginDTO) {
        if (userLoginDTO.password().isEmpty()) {
            throw new CustomException("Password cannot be empty", HttpStatus.BAD_REQUEST.value());
        }

        boolean userExists = checkUserExists(userLoginDTO.email());

        if (!userExists) {
            throw new CustomException("User does not exist", 123);
        }

        // TODO: Implement the rest
    }

    private boolean checkUserExists(String email) {
        // Implement logic to check if the user exists in the database
        return false; // For demonstration purposes
    }

    public List<UserDto> getAllUsers() {
        //TODO: Perform validations
        List<User> users = userJpaRepo.findAll();
        return userMapper.toUserDtos(users);
    }

    public User register(UserDto userDto) {
        //TODO: Perform validations
        User user = userMapper.toUser(userDto);
        return userJpaRepo.save(user);
    }

    public User updateUser(UserDto userDto) {
        //TODO: Perform validations
        Optional<User> find = userJpaRepo.findById(userDto.id());
        if (find.isPresent()) {
            User user = userMapper.toUser(userDto);
            return userJpaRepo.save(user);
        }
        return null;
    }

    public Optional<UserDto> getUserById(UUID id) {
        Optional<User> user = userJpaRepo.findById(id);
        return user.map(value -> userMapper.toUserDto(value));
    }

    public User deleteUser(UUID id) {
        //TODO: Perform validations
        Optional<UserDto> userDto = getUserById(id);
        if (userDto.isPresent()) {
            User user = userMapper.toUser(userDto.get());
            userJpaRepo.delete(user);
            return user;
        }
        return null;
    }

    public void logoutUser() {
        //TODO: implement
    }
}
