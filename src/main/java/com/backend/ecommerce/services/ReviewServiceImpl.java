package com.backend.ecommerce.services;

import com.backend.ecommerce.dtos.ReviewDto;
import com.backend.ecommerce.entities.Review;
import com.backend.ecommerce.mappers.ReviewMapper;
import com.backend.ecommerce.repositories.ReviewJpaRepo;
import com.backend.ecommerce.services.interfaces.ReviewService;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewJpaRepo reviewJpaRepo;
  @Autowired
  private ReviewMapper reviewMapper;

  public ReviewDto createReview(ReviewDto reviewDto) {
    Review review = reviewMapper.toReview(reviewDto);
    Review savedReview = reviewJpaRepo.save(review);
    return reviewMapper.toReviewDto(savedReview);
  }

  public Optional<ReviewDto> getReviewById(UUID id) {
    Optional<Review> review = reviewJpaRepo.findById(id);
    return Optional.ofNullable(
            review.map(value -> reviewMapper.toReviewDto(value))
                    .orElseThrow(() -> new NoSuchElementException(
                            ErrorConstants.ErrorMessage.REVIEW_DOES_NOT_EXIST)));
  }

  public ReviewDto updateReview(ReviewDto reviewDto) {
    Optional<Review> findReview = reviewJpaRepo.findById(reviewDto.id());
    return findReview.map(dto -> {
      Review updatedReview = reviewMapper.toReview(reviewDto);
      Review savedReview = reviewJpaRepo.save(updatedReview);
      return reviewMapper.toReviewDto(savedReview);
    }).orElseThrow(() -> new NoSuchElementException(
            ErrorConstants.ErrorMessage.PRODUCT_DOES_NOT_EXIST));
  }

  public List<ReviewDto> getReviewByUserId(UUID userId) {
    List<Review> reviews = reviewJpaRepo.findByUserId(userId);
    return reviewMapper.toReviewDtos(reviews);
  }

  public List<ReviewDto> getReviewByProductId(UUID productId) {
    List<Review> reviews = reviewJpaRepo.findByProductId(productId);
    return reviewMapper.toReviewDtos(reviews);

  }

  public ReviewDto deleteReview(UUID id) {
    Optional<ReviewDto> reviewDto = getReviewById(id);
    return reviewDto.map(dto -> {
      Review review = reviewMapper.toReview(dto);
      reviewJpaRepo.delete(review);
      return dto;
    }).orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.REVIEW_DOES_NOT_EXIST));
  }
}
