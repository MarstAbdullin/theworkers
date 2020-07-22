package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.entities.StudentInfo;
import ru.itis.javalab.rmrteam.theworkers.repositories.ResumesRepository;
import ru.itis.javalab.rmrteam.theworkers.repositories.StudentsInfoRepository;

import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService{

    @Autowired
    ResumesRepository resumesRepository;

    @Autowired
    StudentsInfoRepository studentsInfoRepository;

    @Override
    public void updateResume(Resume resume) {
        Resume currentResume;
        if (resumesRepository.findById(resume.getId()).isPresent())
            currentResume = resumesRepository.findById(resume.getId()).get();
        else
            return;

        if (resume.getAge() != null)
            currentResume.setAge(resume.getAge());
        if (resume.getCareerObjective() != null)
            currentResume.setCareerObjective(resume.getCareerObjective());
        if (resume.getCertificates() != null)
            currentResume.getCertificates().addAll(resume.getCertificates());
        if (resume.getCitizenship() != null)
            currentResume.setCitizenship(resume.getCitizenship());
        if (resume.getCity() != null)
            currentResume.setCity(resume.getCity());
        if (resume.getReadyToRelocation() != null)
            currentResume.setReadyToRelocation(resume.getReadyToRelocation());
        if (resume.getSex() != null)
            currentResume.setSex(resume.getSex());
        if (resume.getDescription() != null)
            currentResume.setDescription(resume.getDescription());
        if (resume.getEmail() != null)
            currentResume.setEmail(resume.getEmail());
        if (resume.getLanguages() != null)
            currentResume.setLanguages(resume.getLanguages());
        if (resume.getPhoneNumber() != null)
            currentResume.setPhoneNumber(resume.getPhoneNumber());
        if (resume.getUniversity() != null)
            currentResume.setUniversity(resume.getUniversity());
        if (resume.getWorkingExperience() != null)
            currentResume.setWorkingExperience(resume.getWorkingExperience());
        if (resume.getWorkingFulltime() != null)
            currentResume.setWorkingFulltime(resume.getWorkingFulltime());
        if (resume.getWorkingInProject() != null)
            currentResume.setWorkingInProject(resume.getWorkingInProject());

        resumesRepository.save(currentResume);
    }

    @Override
    public void saveResume(Resume resume, Long id) {
        Optional<StudentInfo> studentInfoOptional = studentsInfoRepository.findById(id);
        if (resume != null && studentInfoOptional.isPresent()){
            StudentInfo studentInfo = studentInfoOptional.get();
            resume.setStudentId(studentInfo.getId());
            resumesRepository.save(resume);
        }
    }

    @Override
    public Optional<Resume> getResume(Long id) {
        return resumesRepository.findById(id);
    }

    @Override
    public void confirmResume(Long id) {

        if (resumesRepository.findById(id).isPresent()) {
            Resume resume = resumesRepository.findById(id).get();
            resume.setConfirmedByTeacher(true);
            resumesRepository.save(resume);
        }
    }
}
