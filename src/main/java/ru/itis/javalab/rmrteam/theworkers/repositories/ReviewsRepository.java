package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.rmrteam.theworkers.entities.Review;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
}
