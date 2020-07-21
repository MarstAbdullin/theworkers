package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.rmrteam.theworkers.entities.Tag;
import ru.itis.javalab.rmrteam.theworkers.services.TagService;

import java.util.List;

@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping(value = "/tags")
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = tagService.getTags();
        if (!tags.isEmpty())
            return new ResponseEntity<>(tags, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
