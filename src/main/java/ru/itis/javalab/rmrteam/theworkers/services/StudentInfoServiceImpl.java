package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.dto.StudentInfoDto;
import ru.itis.javalab.rmrteam.theworkers.dto.UserDto;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.StudentsInfoRepository;

import java.util.Optional;

@Service
public class StudentInfoServiceImpl implements StudentInfoService{

    @Autowired
    private StudentsInfoRepository studentsInfoRepository;

    public Optional<StudentInfoDto> getStudentInfo(Long id){
        StudentInfo studentInfo;

        if (studentsInfoRepository.findById(id).isPresent())
            studentInfo = studentsInfoRepository.findById(id).get();
        else
            return Optional.empty();

        User user = studentInfo.getUser();

        return Optional.ofNullable(StudentInfoDto.builder()
                .id(studentInfo.getId())
                .firstName(studentInfo.getFirstName())
                .secondName(studentInfo.getSecondName())
                .age(studentInfo.getAge())
                .courseNumber(studentInfo.getCourseNumber())
                .userDto(UserDto.builder()
                        .email(user.getEmail())
                        .role(user.getRole())
                        .id(user.getId())
                        .build())
                .tags(studentInfo.getTags())
                .photoPath(studentInfo.getPhotoPath())
                .confirmed(studentInfo.getConfirmed())
                .resumes(studentInfo.getResumes())
                .specialty(studentInfo.getSpecialty())
                .teachers(studentInfo.getTeachers())
                .build());
    }

    public void updateStudentInfo(StudentInfoDto studentInfoDto, Long userId) {
        StudentInfo studentInfo;
        if (studentsInfoRepository.findById(userId).isPresent())
            studentInfo = studentsInfoRepository.findById(userId).get();
        else
            return;

        if (studentInfoDto.getFirstName() != null)
            studentInfo.setFirstName(studentInfoDto.getFirstName());
        if (studentInfoDto.getSecondName() != null)
            studentInfo.setSecondName(studentInfoDto.getSecondName());
        if (studentInfoDto.getAge() != null)
            studentInfo.setAge(studentInfoDto.getAge());
        if (studentInfoDto.getTags() != null)
            studentInfo.setTags(studentInfoDto.getTags());
        if (studentInfoDto.getCourseNumber()!= null)
            studentInfo.setCourseNumber(studentInfoDto.getCourseNumber());
        if (studentInfoDto.getPhotoPath() != null)
            studentInfo.setPhotoPath(studentInfoDto.getPhotoPath());
        if (studentInfoDto.getSpecialty() != null)
            studentInfo.setSpecialty(studentInfoDto.getSpecialty());
        if (studentInfoDto.getTeachers() != null)
            studentInfo.setTeachers(studentInfoDto.getTeachers());
        if (studentInfoDto.getTags() != null)
            studentInfo.setTags(studentInfoDto.getTags());

        studentsInfoRepository.save(studentInfo);
    }
}
