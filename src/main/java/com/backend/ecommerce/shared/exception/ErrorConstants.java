package com.backend.ecommerce.shared.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {
    public static class ErrorMessage {
        public static final String USER_DOES_NOT_EXIST = "User does not exist";
        public static final String PASSWORD_NOT_EMPTY = "Password cannot be empty";

    }

    public static class ErrorCode {
        // For demonstration purposes
        public static final int USER_DOES_NOT_EXIST = 4041;
    }
}
