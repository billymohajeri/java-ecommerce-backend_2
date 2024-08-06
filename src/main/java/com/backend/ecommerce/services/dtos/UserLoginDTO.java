package com.backend.ecommerce.services.dtos;

import com.backend.ecommerce.entities.enums.AuthenticationRole;
import lombok.Getter;

@Getter
public class UserLoginDTO {
    private String email;
    private String password;
}
