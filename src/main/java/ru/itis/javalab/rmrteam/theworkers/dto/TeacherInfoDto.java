package ru.itis.javalab.rmrteam.theworkers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.Tag;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherInfoDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String positionInUniversity;
    private String workingExperience;
    private UserDto userDto;
    private Set<StudentInfo> students;
    private Set<Tag> tags;
    private String photoPath;
    private Boolean confirmed;

}
