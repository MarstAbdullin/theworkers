package ru.itis.javalab.rmrteam.theworkers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.rmrteam.theworkers.entities.Review;
import ru.itis.javalab.rmrteam.theworkers.entities.Tag;
import ru.itis.javalab.rmrteam.theworkers.entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
}
