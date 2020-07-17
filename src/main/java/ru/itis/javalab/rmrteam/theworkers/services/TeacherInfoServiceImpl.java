package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.dto.TeacherInfoDto;
import ru.itis.javalab.rmrteam.theworkers.dto.UserDto;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.entities.TeacherInfo;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.TeachersInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherInfoServiceImpl implements TeacherInfoService{

    @Autowired
    private TeachersInfoRepository teachersInfoRepository;

    public Optional<TeacherInfoDto> getTeacherInfo(Long id){
        TeacherInfo teacherInfo;

        if (teachersInfoRepository.findById(id).isPresent())
            teacherInfo = teachersInfoRepository.findById(id).get();
        else
            return Optional.empty();

        User user = teacherInfo.getUser();

        return Optional.ofNullable(TeacherInfoDto.builder()
                .id(teacherInfo.getId())
                .firstName(teacherInfo.getFirstName())
                .secondName(teacherInfo.getSecondName())
                .positionInUniversity(teacherInfo.getPositionInUniversity())
                .workingExperience(teacherInfo.getWorkingExperience())
                .userDto(UserDto.builder()
                        .email(user.getEmail())
                        .role(user.getRole())
                        .id(user.getId())
                        .build())
                .students(teacherInfo.getStudents())
                .tags(teacherInfo.getTags())
                .photoPath(teacherInfo.getPhotoPath())
                .build());
    }

    @Override
    public void saveTeacherInfo(TeacherInfo teacherInfo){
        teachersInfoRepository.save(teacherInfo);
    }

    @Override
    public void updateTeacherInfo(TeacherInfoDto teacherInfoDto, Long id){
        TeacherInfo teacherInfo;
        if (teachersInfoRepository.findById(id).isPresent())
            teacherInfo = teachersInfoRepository.findById(id).get();
        else
            return;

        if (teacherInfoDto.getFirstName() != null)
            teacherInfo.setFirstName(teacherInfoDto.getFirstName());
        if (teacherInfoDto.getSecondName() != null)
            teacherInfo.setSecondName(teacherInfoDto.getSecondName());
        if (teacherInfoDto.getPositionInUniversity() != null)
            teacherInfo.setPositionInUniversity(teacherInfoDto.getPositionInUniversity());
        if (teacherInfoDto.getTags() != null)
            teacherInfo.setTags(teacherInfoDto.getTags());
        if (teacherInfoDto.getWorkingExperience() != null)
            teacherInfo.setWorkingExperience(teacherInfoDto.getWorkingExperience());
        if (teacherInfoDto.getPhotoPath() != null)
            teacherInfo.setPhotoPath(teacherInfoDto.getPhotoPath());

        teachersInfoRepository.save(teacherInfo);
    }

    @Override
    public Optional<List<Resume>> getUnconfirmedResumes(Long teacherId) {
        if (teachersInfoRepository.findById(teacherId).isPresent()) {
            List<Resume> resumes = new ArrayList<>(teachersInfoRepository.findById(teacherId).get().getResumes());
            List<Resume> unconfirmedResumes = new ArrayList<>();
            for (Resume resume : resumes) {
                if (!resume.getConfirmedByTeacher()) {
                    unconfirmedResumes.add(resume);
                }
            }
            return Optional.of(unconfirmedResumes);
        } else {
            return Optional.empty();
        }

    }

}
