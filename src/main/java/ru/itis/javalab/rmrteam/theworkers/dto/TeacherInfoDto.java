package ru.itis.javalab.rmrteam.theworkers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    public static TeacherInfoDto from(TeacherInfo teacherInfo) {
        return TeacherInfoDto.builder()
                .id(teacherInfo.getId())
                .firstName(teacherInfo.getFirstName())
                .secondName(teacherInfo.getSecondName())
                .positionInUniversity(teacherInfo.getPositionInUniversity())
                .workingExperience(teacherInfo.getWorkingExperience())
                .userDto(UserDto.builder()
                        .email(teacherInfo.getUser().getEmail())
                        .role(teacherInfo.getUser().getRole())
                        .id(teacherInfo.getUser().getId())
                        .build())
                .students(teacherInfo.getStudents())
                .tags(teacherInfo.getTags())
                .photoPath(teacherInfo.getPhotoPath())
                .build();
    }

    public static List<TeacherInfoDto> from(List<TeacherInfo> list) {
        return list.stream()
                .map(TeacherInfoDto::from)
                .collect(Collectors.toList());
    }
}
