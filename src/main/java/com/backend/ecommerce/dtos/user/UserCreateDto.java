package com.backend.ecommerce.dtos.user;

import com.backend.ecommerce.entities.enums.AuthenticationRole;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import com.backend.ecommerce.shared.utilities.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCreateDto(
        @NotBlank String firstName,

        @NotBlank String lastName,

        String address,

        @NotBlank @Email String email,
        @NotBlank @Pattern(
                regexp = Constants.PASSWORD_REGEX,
                message = ErrorConstants.ErrorMessage.INVALID_PASSWORD)
        String password,

        @NotBlank
        @Pattern(
                regexp = Constants.PHONE_REGEX,
                message = ErrorConstants.ErrorMessage.INVALID_PHONE)
        String phoneNumber,

        @NotBlank String birthDate,

        AuthenticationRole role
) {
}
