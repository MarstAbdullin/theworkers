package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.dto.TeacherInfoDto;
import ru.itis.javalab.rmrteam.theworkers.entities.Resume;
import ru.itis.javalab.rmrteam.theworkers.entities.TeacherInfo;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.TeacherInfoService;
import ru.itis.javalab.rmrteam.theworkers.services.UsersService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class TeacherRestController {

    @Autowired
    private TeacherInfoService teacherInfoService;

    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping(value = "/teacherProfile")
    public ResponseEntity<?> changeProfile(@RequestBody TeacherInfoDto teacherInfoDto, Authentication authentication) {
        Long infoId = usersService.getUserRoleId(((UserDetailsImpl)authentication.getPrincipal()).getId()).get();
        if (teacherInfoDto.getId().equals(infoId)) {
            teacherInfoService.updateTeacherInfo(teacherInfoDto, ((UserDetailsImpl) authentication.getPrincipal()).getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/teacherProfile/{id}")
    public ResponseEntity<TeacherInfoDto> read(@PathVariable(name = "id") Long id) {
        TeacherInfoDto teacherInfoDto;
        if (teacherInfoService.getTeacherInfo(id).isPresent()) {
            teacherInfoDto = teacherInfoService.getTeacherInfo(id).get();
            return new ResponseEntity<>(teacherInfoDto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/teachers")
    public ResponseEntity<List<TeacherInfo>> read() {
        List<TeacherInfo> teacherInfoList = teacherInfoService.getAllTeachers();
        if (!teacherInfoList.isEmpty())
            return new ResponseEntity<>(teacherInfoList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/unconfirmed")
    public ResponseEntity<List<Resume>> getUnconfirmedResumes(Authentication authentication) {
        Long infoId = usersService.getUserRoleId(((UserDetailsImpl)authentication.getPrincipal()).getId()).get();
        Optional<List<Resume>> unconfirmedResumes = teacherInfoService.getUnconfirmedResumes(infoId);
        return unconfirmedResumes.map(resumes -> new ResponseEntity<>(resumes, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
