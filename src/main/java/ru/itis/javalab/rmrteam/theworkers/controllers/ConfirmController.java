package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.javalab.rmrteam.theworkers.services.ConfirmService;

@Controller
@RequestMapping("/confirm/{link}")
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping
    public ResponseEntity openPage(@PathVariable("link") String link){
        confirmService.isConfirmed(link);
        return ResponseEntity.ok("Success confirm");
    }
}
