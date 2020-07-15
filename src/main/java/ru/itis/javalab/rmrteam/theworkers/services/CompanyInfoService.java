package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.CompanyInfo;
import ru.itis.javalab.rmrteam.theworkers.repositories.CompaniesInfoRepository;

import java.util.Optional;

@Service
public class CompanyInfoService {
    @Autowired
    private CompaniesInfoRepository companiesInfoRepository;

    public Optional<CompanyInfo> getCompanyInfo(Long id){
        return companiesInfoRepository.findById(id);
    }
}
