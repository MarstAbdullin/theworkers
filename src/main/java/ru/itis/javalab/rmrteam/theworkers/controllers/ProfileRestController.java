package ru.itis.javalab.rmrteam.theworkers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.rmrteam.theworkers.entities.Role;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.services.UsersService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class ProfileRestController {

    @Autowired
    UsersService usersService;

    @RequestMapping("/profile")
    void redirectToCurrentProfile(HttpServletResponse response, Authentication authentication) throws IOException {

        Long userId = ((UserDetailsImpl)(authentication.getPrincipal())).getId();
        Role role = usersService.getRole(userId).get();
        Optional<Long> infoId = usersService.getUserRoleId(userId);

        response.sendRedirect("/"+ role.name().toLowerCase() + "Profile/" + infoId);

    }
}
