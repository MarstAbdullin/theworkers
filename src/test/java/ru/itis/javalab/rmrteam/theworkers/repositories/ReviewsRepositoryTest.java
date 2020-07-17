package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.rmrteam.theworkers.config.TestApplicationConfig;
import ru.itis.javalab.rmrteam.theworkers.entities.Review;
import ru.itis.javalab.rmrteam.theworkers.repositories.ReviewsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {ApplicationContext.class,
        TestApplicationConfig.class})
public class ReviewsRepositoryTest {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Test
    @Transactional
    public void findAllReviews() {
        List<Review> reviews = new ArrayList<>();

        Review review1 = Review.builder()
                .author("author")
                .review("the best")
                .build();
        reviewsRepository.save(review1);
        reviews.add(review1);

        Review review2 = Review.builder()
                .author("author")
                .review("the best")
                .build();
        reviewsRepository.save(review2);
        reviews.add(review2);

        assertEquals(reviews, reviewsRepository.findAll());
    }
}
