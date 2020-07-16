package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ProfileRestController {

    @RequestMapping("/profile")
    void redirectToCurrentProfile(HttpServletResponse response, Authentication authentication) throws IOException {

        if (((UserDetailsImpl)(authentication.getPrincipal())).getAuthorities().contains("STUDENT"))
            response.sendRedirect("/studentProfile");
        else if (((UserDetailsImpl)(authentication.getPrincipal())).getAuthorities().contains("TEACHER"))
            response.sendRedirect("/teacherProfile");
        else if (((UserDetailsImpl)(authentication.getPrincipal())).getAuthorities().contains("COMPANY"))
            response.sendRedirect("/companyProfile");

    }
}
