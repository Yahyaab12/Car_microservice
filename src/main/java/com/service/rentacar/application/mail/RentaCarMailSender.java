package com.service.rentacar.application.mail;

import com.service.rentacar.application.mail.exception.CouldNotSendMailException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;

@AllArgsConstructor
public class RentaCarMailSender implements MailSender
{
    private final JavaMailSender javaMailSender;

    @Override
    public void send(MimeMessage mimeMessage) throws CouldNotSendMailException
    {
        javaMailSender.send(mimeMessage);
    }
}
