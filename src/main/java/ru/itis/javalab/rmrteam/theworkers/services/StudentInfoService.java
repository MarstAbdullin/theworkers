package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.repositories.StudentsInfoRepository;

import java.util.Optional;

@Service
public class StudentInfoService {
    @Autowired
    private StudentsInfoRepository studentsInfoRepository;

    public Optional<StudentInfo> getStudentInfo(Long id){
        return studentsInfoRepository.findById(id);
    }
}
