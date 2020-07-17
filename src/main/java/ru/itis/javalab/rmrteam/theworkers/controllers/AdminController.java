package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.rmrteam.theworkers.entities.Tag;
import ru.itis.javalab.rmrteam.theworkers.services.AdminService;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @DeleteMapping(value = "/admin/deleteTag/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long id) {
        adminService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        adminService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/deleteResume/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable(name = "id") Long id) {
        adminService.deleteResume(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/deleteReview/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable(name = "id") Long id) {
        adminService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/confirmUser/{id}")
    public ResponseEntity<?> confirmUser(@PathVariable(name = "id") Long id) {
        adminService.confirmUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/addTag")
    public ResponseEntity<?> addTag(@RequestBody Tag tag) {
        adminService.addTag(tag);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
