package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.dto.TeacherInfoDto;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.entities.TeacherInfo;

import java.util.List;
import java.util.Optional;

public interface TeacherInfoService {

    Optional<TeacherInfoDto> getTeacherInfo(Long id);

    void saveTeacherInfo(TeacherInfo teacherInfo);

    void updateTeacherInfo(TeacherInfoDto teacherInfoDto, Long id);

    Optional<List<Resume>> getUnconfirmedResumes(Long teacherId);

    List<TeacherInfo> getAllTeachers();
}
