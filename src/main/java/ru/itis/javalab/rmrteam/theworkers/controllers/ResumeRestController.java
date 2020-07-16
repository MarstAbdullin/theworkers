package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.dto.TeacherInfoDto;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.ResumeService;

@RestController
public class ResumeRestController {

    @Autowired
    private ResumeService resumeService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping(value = "/resume")
    public ResponseEntity<?> changeResume(@RequestBody Resume resume, Authentication authentication) {
        if (resume.getStudent().getId().equals(((UserDetailsImpl)authentication.getPrincipal()).getUserId())) {
            resumeService.updateResume(resume);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping(value = "/createResume")
    public ResponseEntity<?> createResume(@RequestBody Resume resume, Authentication authentication) {
        if (resume != null) {
            resumeService.saveResume(resume);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/resume/{id}")
    public ResponseEntity<Resume> getResume(@PathVariable(name = "id") Long id) {
        Resume resume;
        if (resumeService.getResume(id).isPresent()) {
            resume = resumeService.getResume(id).get();
            return new ResponseEntity<>(resume, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
