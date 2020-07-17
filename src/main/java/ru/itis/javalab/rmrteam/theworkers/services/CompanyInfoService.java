package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.dto.CompanyInfoDto;

import java.util.Optional;

public interface CompanyInfoService {

    Optional<CompanyInfoDto> getCompanyInfo(Long id);

    void updateCompanyInfo(CompanyInfoDto companyInfoDto, Long infoId);
}
