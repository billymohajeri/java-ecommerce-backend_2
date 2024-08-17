package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewJpaRepo extends JpaRepository<Review, UUID> {

  List<Review> findByUserId(UUID userId);

  List<Review> findByProductId(UUID productId);

}
