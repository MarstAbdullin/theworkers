package ru.itis.javalab.rmrteam.theworkers.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.dto.SignInDto;
import ru.itis.javalab.rmrteam.theworkers.dto.TokenDto;
import ru.itis.javalab.rmrteam.theworkers.entities.User;
import ru.itis.javalab.rmrteam.theworkers.repositories.UsersRepository;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.details.UserDetailsImpl;
import ru.itis.javalab.rmrteam.theworkers.security.jwt.utils.JwtUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  SignInServiceImpl implements SignInService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public TokenDto signIn(SignInDto signInDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        System.out.println(jwt);

        return new TokenDto(jwt,
                userDetails.getUsername(),
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getRole().name());
    }
}
