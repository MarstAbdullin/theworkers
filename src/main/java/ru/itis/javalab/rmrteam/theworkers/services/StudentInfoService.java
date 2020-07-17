package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.dto.StudentInfoDto;

import java.util.Optional;

public interface StudentInfoService {

    Optional<StudentInfoDto> getStudentInfo(Long id);

    void updateStudentInfo(StudentInfoDto studentInfoDto, Long userId);

}
