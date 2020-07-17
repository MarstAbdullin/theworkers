package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.dto.StudentInfoDto;
import ru.itis.javalab.rmrteam.theworkers.dto.UserDto;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.StudentsInfoRepository;

import java.util.List;
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

        return Optional.ofNullable(StudentInfoDto.from(studentInfo));
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

    @Override
    public List<StudentInfoDto> getAllStudents() {
        return StudentInfoDto.from(studentsInfoRepository.findAll());
    }
}
