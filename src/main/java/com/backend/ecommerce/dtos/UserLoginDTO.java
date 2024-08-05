package com.backend.ecommerce.dtos;

import com.backend.ecommerce.enums.AuthenticationRole;
import lombok.Getter;

@Getter
public class UserLoginDTO {
    private String email;
    private String password;
    private AuthenticationRole role;
}
