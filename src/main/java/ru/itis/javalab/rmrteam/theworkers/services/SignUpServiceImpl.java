package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.dto.CompanyInfoDto;
import ru.itis.javalab.rmrteam.theworkers.dto.SignUpDto;
import ru.itis.javalab.rmrteam.theworkers.dto.StudentInfoDto;
import ru.itis.javalab.rmrteam.theworkers.dto.TeacherInfoDto;
import ru.itis.javalab.rmrteam.theworkers.entities.CompanyInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.TeacherInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.CompaniesInfoRepository;
import ru.itis.javalab.rmrteam.theworkers.repositories.StudentsInfoRepository;
import ru.itis.javalab.rmrteam.theworkers.repositories.TeachersInfoRepository;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompaniesInfoRepository companiesInfoRepository;

    @Autowired
    private StudentsInfoRepository studentsInfoRepository;

    @Autowired
    private TeachersInfoRepository teachersInfoRepository;

    @Override
    public void signUp(SignUpDto signUpDto, String confirmLink) {
        if (!usersRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            User user = User.builder()
                    .email(signUpDto.getEmail())
                    .hash(passwordEncoder.encode(signUpDto.getPassword()))
                    .role(signUpDto.getRole())
                    .build();
            usersRepository.save(user);
        } else throw new IllegalArgumentException("Пользователь с таким email уже существует");
    }

    @Override
    public void registerStudent(StudentInfoDto studentInfoDto, Long id) {
        User user = usersRepository.findById(id).get();
        studentsInfoRepository.save(StudentInfo.builder()
                .user(user)
                .age(studentInfoDto.getAge())
                .courseNumber(studentInfoDto.getCourseNumber())
                .firstName(studentInfoDto.getFirstName())
                .photoPath(studentInfoDto.getPhotoPath())
                .secondName(studentInfoDto.getSecondName())
                .specialty(studentInfoDto.getSpecialty())
                .tags(studentInfoDto.getTags())
                .teachers(studentInfoDto.getTeachers())
                .build());
        usersRepository.save(user);
    }

    @Override
    public void registerCompany(CompanyInfoDto companyInfoDto, Long id) {
        User user = usersRepository.findById(id).get();
        companiesInfoRepository.save(CompanyInfo.builder()
                .user(user)
                .address(companyInfoDto.getAddress())
                .companyName(companyInfoDto.getCompanyName())
                .logoPath(companyInfoDto.getLogoPath())
                .phoneNumber(companyInfoDto.getPhoneNumber())
                .tags(companyInfoDto.getTags())
                .build());
    }

    @Override
    public void registerTeacher(TeacherInfoDto teacherInfoDto, Long id) {
        User user = usersRepository.findById(id).get();
        teachersInfoRepository.save(TeacherInfo.builder()
                .user(user)
                .photoPath(teacherInfoDto.getPhotoPath())
                .tags(teacherInfoDto.getTags())
                .workingExperience(teacherInfoDto.getWorkingExperience())
                .firstName(teacherInfoDto.getFirstName())
                .secondName(teacherInfoDto.getSecondName())
                .positionInUniversity(teacherInfoDto.getPositionInUniversity())
                .build());
    }
}
