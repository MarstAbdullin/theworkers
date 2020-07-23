package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.ResumeService;
import ru.itis.javalab.rmrteam.theworkers.services.UsersService;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200", "http://localhost:8081"})
@RestController
public class ResumeRestController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/resume")
    public ResponseEntity<?> changeResume(@RequestBody Resume resume, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long infoId = usersService.getUserRoleId(((UserDetailsImpl) authentication.getPrincipal()).getId()).get();
        if (userDetails.getRole().equals(Role.STUDENT) && resume.getStudentId().equals(infoId)) {
            resumeService.updateResume(resume);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/createResume")
    public ResponseEntity<?> createResume(@RequestBody Resume resume, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long infoId = usersService.getUserRoleId(((UserDetailsImpl) authentication.getPrincipal()).getId()).get();
        if (userDetails.getRole().equals(Role.STUDENT)) {
            resumeService.saveResume(resume, infoId);
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

    @GetMapping(value = "/resume/{id}/confirm")
    public ResponseEntity<?> confirmResume(@PathVariable(name = "id") Long id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long infoId = userDetails.getId();
        if (userDetails.getRole().equals(Role.TEACHER) && resumeService.getResume(id).isPresent()) {
            if (resumeService.getResume(id).get().getTeacherId().equals(infoId)) {
                resumeService.confirmResume(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
