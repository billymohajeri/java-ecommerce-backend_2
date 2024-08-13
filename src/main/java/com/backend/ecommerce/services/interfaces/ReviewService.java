package com.backend.ecommerce.services.interfaces;

import com.backend.ecommerce.dtos.ReviewDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

  ReviewDto createReview(ReviewDto reviewDto);

  ReviewDto updateReview(ReviewDto reviewDto);

  List<ReviewDto> getReviewByUserId(UUID userId);

  List<ReviewDto> getReviewByProductId(UUID productId);

  ReviewDto deleteReview(UUID id);
}
