package com.backend.ecommerce.mappers;

import com.backend.ecommerce.dtos.ReviewDto;
import com.backend.ecommerce.entities.Review;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)

public interface ReviewMapper {

  Review toReview(ReviewDto source);

  ReviewDto toReviewDto(Review source);
}
