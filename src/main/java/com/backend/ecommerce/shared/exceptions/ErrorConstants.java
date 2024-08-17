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
    public static final String INVALID_PASSWORD = "Invalid Password";

    public static final String PRODUCT_DOES_NOT_EXIST = "This product does not exist";

    public static final String REVIEW_DOES_NOT_EXIST = "This review does not exist";

    public static final String ORDER_DOES_NOT_EXIST = "Order does not exist.";
    public static final String ORDER_DATE_DOES_NOT_EXIST = "Date and time are required";
    public static final String ORDER_STATUS_DOES_NOT_EXIST = "Order status is required";
    public static final String ORDER_ADDRESS_DOES_NOT_EXIST = "Address is required";
    public static final String ORDER_ADDRESS_LIMIT = "Address should not exceed 255 characters";
    public static final String ORDER_DATE_LIMIT = "Date and time must be in the past or present";

    public static final String PAYMENT_DOES_NOT_EXIST = "Payment does not exist.";
    public static final String PAYMENT_ALREADY_EXIST = "Payment already exists for this order";
    public static final String USER_ID_REQUIRED = "User id is required!";
    public static final String ORDER_ID_REQUIRED = "Order id is required!";
    public static final String PAYMENT_AMOUNT_REQUIRED = "Amount is required!";
    public static final String PAYMENT_AMOUNT_LIMIT = "Amount must be greater than zero";
    public static final String PAYMENT_STATUS_REQUIRED = "Payment Status is required!";
    public static final String PAYMENT_METHOD_REQUIRED = "Payment Method is required!";
  }

  public static class ErrorCode {
    // For demonstration purposes
    public static final int USER_DOES_NOT_EXIST = 4041;
  }
}
