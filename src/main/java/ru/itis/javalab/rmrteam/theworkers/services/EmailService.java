package ru.itis.javalab.rmrteam.theworkers.services;

import ru.itis.javalab.rmrteam.theworkers.entities.Mail;

public interface EmailService {
    void sendMessage(Mail mail, String viewName);
}
