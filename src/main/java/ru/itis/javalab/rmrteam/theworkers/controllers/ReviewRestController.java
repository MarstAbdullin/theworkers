package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.entities.Review;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.ReviewService;

import java.util.List;

@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class ReviewRestController {

    @Autowired
    ReviewService reviewService;


    @PostMapping(value = "/company/{id}/createReview")
    public ResponseEntity<?> createReview(@RequestBody Review review, @PathVariable(name = "id") Long id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        if ((review != null) && userDetails.getRole().equals(Role.STUDENT)) {
            reviewService.saveReview(review, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/company/{id}/reviews")
    public ResponseEntity<List<Review>> getReview(@PathVariable(name = "id") Long id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        List<Review> reviews;
        if (userDetails.getRole().equals(Role.STUDENT) && reviewService.getReviews(id).isPresent()) {
            reviews = reviewService.getReviews(id).get();
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
