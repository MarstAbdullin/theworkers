package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.dto.StudentInfoDto;
import ru.itis.javalab.rmrteam.theworkers.dto.TeacherInfoDto;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.StudentInfoService;
import ru.itis.javalab.rmrteam.theworkers.services.TeacherInfoService;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentRestController {

    @Autowired
    private StudentInfoService studentInfoService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping(value = "/studentProfile")
    public ResponseEntity<?> changeProfile(@RequestBody StudentInfoDto studentInfoDto, Authentication authentication) {
        if (studentInfoDto.getId().equals(((UserDetailsImpl)authentication.getPrincipal()).getUserId())) {
            studentInfoService.updateStudentInfo(studentInfoDto, ((UserDetailsImpl) authentication.getPrincipal()).getUserId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/teacherProfile/{id}")
    public ResponseEntity<StudentInfoDto> read(@PathVariable(name = "id") Long id) {
        StudentInfoDto studentInfoDto;
        if (studentInfoService.getStudentInfo(id).isPresent()) {
            studentInfoDto = studentInfoService.getStudentInfo(id).get();
            return new ResponseEntity<>(studentInfoDto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
