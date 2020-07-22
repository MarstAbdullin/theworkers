package ru.itis.javalab.rmrteam.theworkers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;

import java.util.List;
import java.util.Set;

public interface ResumesRepository extends JpaRepository<Resume, Long> {
    Set<Resume> findAllByStudentId(Long studentId);
    Set<Resume> findAllByConfirmedByTeacherAndTeacherId(Boolean confirmedByTeacher, Long teacherId);
}
