package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.TeacherInfo;
import ru.itis.javalab.rmrteam.theworkers.repositories.TeachersInfoRepository;

import java.util.Optional;

@Service
public class TeacherInfoService {

    @Autowired
    private TeachersInfoRepository teachersInfoRepository;

    private Optional<TeacherInfo> getTeacherInfo(Long id){
        return teachersInfoRepository.findById(id);
    }
}
