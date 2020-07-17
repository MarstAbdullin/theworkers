package ru.itis.javalab.rmrteam.theworkers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.rmrteam.theworkers.entities.CompanyInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.Review;
import ru.itis.javalab.rmrteam.theworkers.entities.Tag;
import ru.itis.javalab.rmrteam.theworkers.entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyInfoDto {

    private Long id;
    private String companyName;
    private String address;
    private String logoPath;
    private String phoneNumber;
    private UserDto userDto;
    private Set<Tag> tags;

    public static CompanyInfoDto from(CompanyInfo companyInfo) {
        return CompanyInfoDto.builder()
                .address(companyInfo.getAddress())
                .companyName(companyInfo.getCompanyName())
                .id(companyInfo.getId())
                .logoPath(companyInfo.getLogoPath())
                .phoneNumber(companyInfo.getPhoneNumber())
                .tags(companyInfo.getTags())
                .userDto(UserDto.from(companyInfo.getUser()))
                .build();
    }

    public static List<CompanyInfoDto> from(List<CompanyInfo> list) {
        return list.stream()
                .map(CompanyInfoDto::from)
                .collect(Collectors.toList());
    }
}
