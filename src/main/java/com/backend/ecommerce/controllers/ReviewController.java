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

  @PutMapping
  public ResponseEntity<GlobalResponse<ReviewDto>> updateReview(@RequestBody ReviewDto reviewDto) {
    return ResponseEntity.ok(new GlobalResponse<>(reviewService.updateReview(reviewDto), null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<GlobalResponse<List<ReviewDto>>> getReviewByUserId(@PathVariable UUID id) {
    List<ReviewDto> reviews = reviewService.getReviewByUserId(id);
    GlobalResponse<List<ReviewDto>> response = new GlobalResponse<>(reviews, null);
    return ResponseEntity.ok(response);
  }
}
