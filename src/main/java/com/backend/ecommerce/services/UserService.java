package com.backend.ecommerce.services;

import com.backend.ecommerce.services.dtos.UserLoginDTO;
import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.repositories.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserJpaRepo userJpaRepo;

    public void loginUser(UserLoginDTO userLoginDTO) {
        //TODO: implement
    }

    public List<User> getAllUsers() {
        //TODO: Perform validations
        return userJpaRepo.findAll();
    }

    public User register(User user) {
        //TODO: Perform validations
        return userJpaRepo.save(user);
    }

    public User updateUser(User user) {
        //TODO: Perform validations
        Optional<User> find = userJpaRepo.findById(user.getId());
        if (find.isPresent()) {
            return userJpaRepo.save(user);
        }
        return user;
    }

    public Optional<User> getUserById(UUID id) {
        return userJpaRepo.findById(id);
    }

    public Optional<User> deleteUser(UUID id) {
        //TODO: Perform validations
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            userJpaRepo.delete(user.get());
            return user;
        }
        return Optional.empty();
    }

    public void logoutUser() {
        //TODO: implement
    }

    public User addUser(User user) {
        //TODO: Add admin authentication
        return userJpaRepo.save(user);
    }
}
