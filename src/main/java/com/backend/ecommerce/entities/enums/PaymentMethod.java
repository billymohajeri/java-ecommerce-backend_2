package com.backend.ecommerce.entities.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CREDIT_CARD(1),
    BANK_TRANSFER(2),
    CASH(3);
    private final int code;
    PaymentMethod(int code){
        this.code = code;
    }

    public static PaymentMethod fromCode(int code){
        for (PaymentMethod method : PaymentMethod.values()){
            if (method.getCode() == code){
                return method;
            }
        }
        throw new IllegalArgumentException("Unknown code: "+ code);
    }
}
