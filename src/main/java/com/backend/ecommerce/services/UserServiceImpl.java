package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.user.UserCreateDto;
import com.backend.ecommerce.dtos.user.UserDto;
import com.backend.ecommerce.dtos.user.UserLoginDto;
import com.backend.ecommerce.dtos.user.UserLoginResponseDto;
import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.repositories.UserJpaRepo;
import com.backend.ecommerce.mappers.UserMapper;
import com.backend.ecommerce.services.interfaces.UserService;
import com.backend.ecommerce.shared.exceptions.CustomException;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import com.backend.ecommerce.shared.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private UserJpaRepo userJpaRepo;
    @Autowired
    private UserMapper userMapper;

    public UserLoginResponseDto loginUser(UserLoginDto userLoginDTO) {
        User user = userJpaRepo.findByEmail(userLoginDTO.email())
                .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.USER_DOES_NOT_EXIST));
        String token = authService.authenticate(userLoginDTO);
        return new UserLoginResponseDto(token, userMapper.toUserDto(user));
    }

    private boolean checkUserExists(UUID id) {
        if (id != null) {
            return userJpaRepo.findById(id).isPresent();
        }
        return false;
    }

    private boolean checkUserExists(String email) {
        if (email != null && !email.isEmpty()) {
            return userJpaRepo.findByEmail(email).isPresent();
        }
        return false;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userJpaRepo.findAll();
        return userMapper.toUserDtos(users);
    }

    public UserDto register(UserCreateDto userCreateDto) {
        boolean emailExists = checkUserExists(userCreateDto.email());
        boolean phoneExists = userJpaRepo.findByPhoneNumber(userCreateDto.phoneNumber()).isPresent();
        if (emailExists || phoneExists) {
            throw new CustomException(ErrorConstants.ErrorMessage.USER_ALREADY_PRESENT, HttpStatus.CONFLICT.value());
        }

        User user = authService.register(userCreateDto);
        return userMapper.toUserDto(user);
    }

    public UserDto updateUser(UserDto userDto) {
        Optional<User> find = userJpaRepo.findById(userDto.id());
        if (find.isEmpty()) {
            throw new NoSuchElementException(ErrorConstants.ErrorMessage.USER_DOES_NOT_EXIST);
        }
        find.map(user -> {
            user.setAddress(userDto.address());
            user.setFirstName(userDto.firstName());
            user.setLastName(userDto.lastName());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.SERVER_DATE_FORMAT);
            user.setBirthDate(LocalDate.parse(userDto.birthDate(), formatter));
            if (!userDto.email().equals(user.getEmail()) && !userDto.email().isEmpty()) {
                boolean userExists = checkUserExists(userDto.email());
                if (userExists) {
                    throw new CustomException(ErrorConstants.ErrorMessage.EMAIL_ALREADY_PRESENT, HttpStatus.CONFLICT.value());
                }
                user.setEmail(userDto.email());
            }

            if (!userDto.phoneNumber().equals(user.getPhoneNumber()) && !userDto.phoneNumber().isEmpty()) {
                boolean userExists = userJpaRepo.findByPhoneNumber(userDto.phoneNumber()).isPresent();
                if (userExists) {
                    throw new CustomException(ErrorConstants.ErrorMessage.PHONE_ALREADY_PRESENT, HttpStatus.CONFLICT.value());
                }
                user.setPhoneNumber(userDto.phoneNumber());
            }
            return user;
        });
        User updatedUser = userJpaRepo.save(find.get());
        return userMapper.toUserDto(updatedUser);
    }

    public Optional<UserDto> getUserById(UUID id) {
        Optional<User> user = userJpaRepo.findById(id);
        return Optional.ofNullable(
                user.map(value -> userMapper.toUserDto(value))
                        .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.USER_DOES_NOT_EXIST)));
    }

    public UserDto deleteUser(UUID id) {
        Optional<UserDto> userDto = getUserById(id);
        return userDto.map(dto -> {
            User user = userMapper.toUser(dto);
            userJpaRepo.delete(user);
            return dto;
        }).orElseThrow();
    }

    public void logoutUser() {
        //TODO: implement
    }
}
