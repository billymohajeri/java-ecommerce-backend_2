package com.backend.ecommerce.entities.enums;

public enum OrderStatus {
    PENDING(1),
    SHIPPED(2),
    DELIVERED(3),
    CANCELED(4);
    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus fromCode(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
