package com.backend.ecommerce.services.dtos;

import java.util.UUID;

//TODO: cartDto will have products as well
public record CartDto(UUID userId) {
}
