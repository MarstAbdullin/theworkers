package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.dto.StudentInfoDto;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.StudentInfoService;
import ru.itis.javalab.rmrteam.theworkers.services.UsersService;

@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class StudentRestController {

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/studentProfile")
    public ResponseEntity<?> changeProfile(@RequestBody StudentInfoDto studentInfoDto, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        if (userDetails.getRole().equals(Role.STUDENT)) {
            studentInfoService.updateStudentInfo(studentInfoDto, userDetails.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/studentProfile/{id}")
    public ResponseEntity<StudentInfoDto> getStudent(@PathVariable(name = "id") Long id) {
        StudentInfoDto studentInfoDto;
        if (studentInfoService.getStudentInfo(id).isPresent()) {
            studentInfoDto = studentInfoService.getStudentInfo(id).get();
            return new ResponseEntity<>(studentInfoDto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
