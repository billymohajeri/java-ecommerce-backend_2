package com.backend.ecommerce.repositories;

import com.backend.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserJpaRepo extends JpaRepository<User, UUID> {
}
