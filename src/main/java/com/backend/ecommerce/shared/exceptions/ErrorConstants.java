package com.backend.ecommerce.shared.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {
  public static class ErrorMessage {
    public static final String USER_DOES_NOT_EXIST = "This user does not exist";
    public static final String USER_ALREADY_PRESENT = "This user is already present";
    public static final String PHONE_ALREADY_PRESENT = "This email already exists";
    public static final String EMAIL_ALREADY_PRESENT = "This phone already exists";

    public static final String PASSWORD_NOT_EMPTY = "Password cannot be empty";
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
    public static final String INVALID_PHONE = "Invalid Phone Number";

    public static final String PRODUCT_DOES_NOT_EXIST = "This product does not exist";

    public static final String ORDER_NOT_FOUND = "Order not found.";
    public static final String PAYMENT_NOT_FOUND = "Payment not found.";
  }

  public static class ErrorCode {
    // For demonstration purposes
    public static final int USER_DOES_NOT_EXIST = 4041;
  }
}
