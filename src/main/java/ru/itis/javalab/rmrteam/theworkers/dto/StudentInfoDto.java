package ru.itis.javalab.rmrteam.theworkers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.entities.Tag;
import ru.itis.javalab.rmrteam.theworkers.entities.TeacherInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoDto {

    private Long id;
    private Integer age;
    private Integer courseNumber;
    private String specialty;
    private String firstName;
    private String secondName;
    private String photoPath;
    private Set<TeacherInfo> teachers;
    private Set<Tag> tags;
    private Set<Resume> resumes;
    private UserDto userDto;
    private Boolean confirmed;
}
