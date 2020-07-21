package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.dto.CompanyInfoDto;
import ru.itis.javalab.rmrteam.theworkers.dto.SignUpDto;
import ru.itis.javalab.rmrteam.theworkers.dto.StudentInfoDto;
import ru.itis.javalab.rmrteam.theworkers.dto.TeacherInfoDto;

public interface SignUpService {

    Long signUp(SignUpDto signUpDto, String confirmLink);

    void registerStudent(StudentInfoDto studentInfoDto, Long id);

    void registerCompany(CompanyInfoDto companyInfoDto, Long id);

    void registerTeacher(TeacherInfoDto teacherInfoDto, Long id);
}
