package ru.itis.javalab.rmrteam.theworkers.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.rmrteam.theworkers.dto.SignUpDto;
import ru.itis.javalab.rmrteam.theworkers.entities.Mail;
import ru.itis.javalab.rmrteam.theworkers.services.EmailService;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class EmailSenderLinkFileAspect {

   @Autowired
   private EmailService emailService;

    @AfterReturning(value = "execution(* ru.itis.javalab.rmrteam.theworkers.services.SignUpService.signUp())")
    public void sendConfirmation(JoinPoint joinPoint) {
        SignUpDto user = (SignUpDto) joinPoint.getArgs()[0];
        String email = user.getEmail();
        String link = (String) joinPoint.getArgs()[1];

        if (email == null) {
            throw new IllegalArgumentException("email is null");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("link", link);

        Mail mail = Mail.builder()
                .to(email)
                .from("no-reply@gmail.com")
                .subject("Confirm email")
                .model(model)
                .build();

        emailService.sendMessage(mail, "/static/ftl/confirm_email.ftl");
    }

}