package com.backend.ecommerce.shared.exceptions;

public class OrderAndPaymentExceptions{
    public static class OrderNotFoundException extends CustomException {
        public OrderNotFoundException() {
            super(ErrorConstants.ErrorMessage.ORDER_NOT_FOUND, 404);
        }
    }
    public static class PaymentNotFoundException extends CustomException {
        public PaymentNotFoundException() {
            super(ErrorConstants.ErrorMessage.PAYMENT_NOT_FOUND, 404);
        }
    }

    public static class PaymentAlreadyExistsException extends CustomException {
        public PaymentAlreadyExistsException() {
            super("Payment already exists for this order", 400);
        }
    }
}
