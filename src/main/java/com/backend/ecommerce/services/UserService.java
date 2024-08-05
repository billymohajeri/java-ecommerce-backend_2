package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.UserLoginDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void loginUser(UserLoginDTO userLoginDTO) {
        System.out.println("login email: " + userLoginDTO.getEmail());
    }
}
