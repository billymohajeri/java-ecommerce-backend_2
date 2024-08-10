package com.backend.ecommerce.dtos.user;

import com.backend.ecommerce.entities.enums.AuthenticationRole;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import com.backend.ecommerce.shared.utilities.Constants;
import jakarta.validation.constraints.*;

import java.util.UUID;

public record UserDto(
        UUID id,

        @NotBlank String firstName,

        @NotBlank String lastName,

        String address,

        @NotBlank @Email String email,

        @NotBlank
        @Pattern(
                regexp = Constants.PHONE_REGEX,
                message = ErrorConstants.ErrorMessage.INVALID_PHONE)
        String phoneNumber,

        @NotBlank String birthDate,

        @NotNull AuthenticationRole role
) {
}
