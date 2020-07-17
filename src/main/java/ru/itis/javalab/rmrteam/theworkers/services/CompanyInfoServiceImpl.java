package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.dto.CompanyInfoDto;
import ru.itis.javalab.rmrteam.theworkers.dto.UserDto;
import ru.itis.javalab.rmrteam.theworkers.entities.CompanyInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.TeacherInfo;
import ru.itis.javalab.rmrteam.theworkers.repositories.CompaniesInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    private CompaniesInfoRepository companiesInfoRepository;

    public Optional<CompanyInfoDto> getCompanyInfo(Long id){
        Optional<CompanyInfo> companyInfoOptional = companiesInfoRepository.findById(id);
        if (companyInfoOptional.isPresent()){
            CompanyInfo companyInfo = companyInfoOptional.get();
            return Optional.ofNullable(CompanyInfoDto.from(companyInfo));
        } else
            return Optional.empty();
    }

    @Override
    public void updateCompanyInfo(CompanyInfoDto companyInfoDto, Long infoId) {
        CompanyInfo companyInfo;
        if (companiesInfoRepository.findById(infoId).isPresent())
            companyInfo = companiesInfoRepository.findById(infoId).get();
        else
            return;

        if (companyInfoDto.getAddress() != null)
            companyInfo.setAddress(companyInfoDto.getAddress());
        if (companyInfoDto.getCompanyName() != null)
            companyInfo.setCompanyName(companyInfoDto.getCompanyName());
        if (companyInfoDto.getId() != null)
            companyInfo.setId(companyInfoDto.getId());
        if (companyInfoDto.getTags() != null)
            companyInfo.setTags(companyInfoDto.getTags());
        if (companyInfoDto.getLogoPath() != null)
            companyInfo.setLogoPath(companyInfoDto.getLogoPath());
        if (companyInfoDto.getPhoneNumber() != null)
            companyInfo.setPhoneNumber(companyInfoDto.getPhoneNumber());

        companiesInfoRepository.save(companyInfo);
    }

    @Override
    public List<CompanyInfoDto> getAllCompany() {
        return CompanyInfoDto.from(companiesInfoRepository.findAll());
    }
}
