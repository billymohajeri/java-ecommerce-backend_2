package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.ReviewDto;

import java.util.Optional;
import java.util.UUID;

public interface ReviewService {

  ReviewDto createReview(ReviewDto reviewDto);

  ReviewDto updateReview(ReviewDto reviewDto);

  Optional<ReviewDto> getReviewByUserId(UUID userId);

  Optional<ReviewDto> getReviewByProductId(UUID productId);

  ReviewDto deleteReview(UUID id);
}
