package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    User findOne();
    List<User> findAll();
}
