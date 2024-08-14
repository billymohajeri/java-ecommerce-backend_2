package com.backend.ecommerce.controllers;

import com.backend.ecommerce.dtos.ReviewDto;
import com.backend.ecommerce.services.interfaces.ReviewService;
import com.backend.ecommerce.shared.response.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  @PostMapping
  public ResponseEntity<GlobalResponse<ReviewDto>> createReview(@RequestBody ReviewDto reviewDto) {
    return ResponseEntity.ok(new GlobalResponse<>(reviewService.createReview(reviewDto), null));
  }

  @PutMapping("/{id}")
  public ResponseEntity<GlobalResponse<ReviewDto>> updateReview(@PathVariable UUID id, @RequestBody ReviewDto reviewDto) {
    return ResponseEntity.ok(new GlobalResponse<>(reviewService.updateReview(id, reviewDto), null));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<GlobalResponse<List<ReviewDto>>> getReviewByUserId(@PathVariable UUID userId) {
    List<ReviewDto> reviews = reviewService.getReviewByUserId(userId);
    GlobalResponse<List<ReviewDto>> response = new GlobalResponse<>(reviews, null);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<GlobalResponse<List<ReviewDto>>> getReviewByProductId(@PathVariable UUID productId) {
    List<ReviewDto> reviews = reviewService.getReviewByProductId(productId);
    GlobalResponse<List<ReviewDto>> response = new GlobalResponse<>(reviews, null);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GlobalResponse<ReviewDto>> deleteReview(@PathVariable UUID id) {
    GlobalResponse<ReviewDto> response = new GlobalResponse<>(reviewService.deleteReview(id), null);
    return ResponseEntity.ok(response);
  }
}
