package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.entities.Review;
import ru.itis.javalab.rmrteam.theworkers.services.ReviewService;

import java.util.List;

@RestController
public class ReviewRestController {

    @Autowired
    ReviewService reviewService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping(value = "/company/{id}/createReview")
    public ResponseEntity<?> createReview(@RequestBody Review review, @PathVariable(name = "id") Long id) {
        if (review != null) {
            reviewService.saveReview(review, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping(value = "/company/{id}/reviews")
    public ResponseEntity<List<Review>> getReview(@PathVariable(name = "id") Long id) {
        List<Review> reviews;
        if (reviewService.getReviews(id).isPresent()) {
            reviews = reviewService.getReviews(id).get();
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
