package ru.itis.javalab.rmrteam.theworkers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.Tag;
import ru.itis.javalab.rmrteam.theworkers.entities.TeacherInfo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static StudentInfoDto from(StudentInfo studentInfo) {
        return StudentInfoDto.builder()
                .id(studentInfo.getId())
                .firstName(studentInfo.getFirstName())
                .secondName(studentInfo.getSecondName())
                .age(studentInfo.getAge())
                .courseNumber(studentInfo.getCourseNumber())
                .userDto(UserDto.builder()
                        .email(studentInfo.getUser().getEmail())
                        .role(studentInfo.getUser().getRole())
                        .id(studentInfo.getUser().getId())
                        .build())
                .tags(studentInfo.getTags())
                .photoPath(studentInfo.getPhotoPath())
                .resumes(studentInfo.getResumes())
                .specialty(studentInfo.getSpecialty())
                .teachers(studentInfo.getTeachers())
                .build();
    }

    public static List<StudentInfoDto> from(List<StudentInfo> list) {
        return list.stream()
                .map(StudentInfoDto::from)
                .collect(Collectors.toList());
    }
}
