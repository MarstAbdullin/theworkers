package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.ResumeService;
import ru.itis.javalab.rmrteam.theworkers.services.UsersService;

@RestController
public class ResumeRestController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping(value = "/resume")
    public ResponseEntity<?> changeResume(@RequestBody Resume resume, Authentication authentication) {
        Long infoId = usersService.getUserRoleId(((UserDetailsImpl)authentication.getPrincipal()).getUserId()).get();
        if (resume.getStudent().getId().equals(infoId)) {
            resumeService.updateResume(resume);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping(value = "/createResume")
    public ResponseEntity<?> createResume(@RequestBody Resume resume, @PathVariable(name = "id") Long id) {
        if (resume != null) {
            resumeService.saveResume(resume, id);
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

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping(value = "/resume/{id}/confirm")
    public ResponseEntity<?> confirmResume(@PathVariable(name = "id") Long id, Authentication authentication) {
        Resume resume;
        Long infoId = usersService.getUserRoleId(((UserDetailsImpl)authentication.getPrincipal()).getUserId()).get();
        if (resumeService.getResume(id).isPresent()) {
            resume = resumeService.getResume(id).get();
            if (resume.getTeacherInfo().getId().equals(infoId)) {
                resumeService.confirmResume(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
