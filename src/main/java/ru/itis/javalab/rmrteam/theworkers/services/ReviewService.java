package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.entities.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    void saveReview(Review review, Long id);

    Optional<List<Review>> getReviews(Long id);

}
