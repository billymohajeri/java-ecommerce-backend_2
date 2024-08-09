package com.backend.ecommerce.entities.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING(1),
    COMPLETED(2),
    FAILED(3),
    REFUNDED(4);
    private final int code;
    PaymentStatus(int code){
        this.code=code;
    }

    public static PaymentStatus fromCode(int code){
        for (PaymentStatus status : PaymentStatus.values()){
            if (status.getCode() == code){
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown code: "+ code);
    }

}
