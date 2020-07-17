package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.CompanyInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.Review;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.repositories.CompaniesInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    CompaniesInfoRepository companiesInfoRepository;

    @Override
    public void saveReview(Review review, Long id) {
        Optional<CompanyInfo> companyInfoOptional = companiesInfoRepository.findById(id);
        if (review != null && companyInfoOptional.isPresent()){
            CompanyInfo companyInfo = companyInfoOptional.get();
            companyInfo.getReviews().add(review);
            companiesInfoRepository.save(companyInfo);
        }
    }

    @Override
    public Optional<List<Review>> getReviews(Long id) {
        Optional<CompanyInfo> companyInfoOptional = companiesInfoRepository.findById(id);
        if (companyInfoOptional.isPresent()) {
            CompanyInfo companyInfo = companyInfoOptional.get();
            return Optional.of(new ArrayList<>(companyInfo.getReviews()));
        } else
            return Optional.empty();
    }
}
