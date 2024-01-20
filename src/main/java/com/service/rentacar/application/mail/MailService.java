package com.service.rentacar.application.mail;

import com.service.rentacar.application.mail.exception.CouldNotSendMailException;
import jakarta.mail.internet.MimeMessage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service

@Setter
@Slf4j
public class MailService
{
    @Autowired
    private final MailSender mailSender;
    private final MailCreator mailCreator;


    public void generateAndSend(MailMessage message) throws CouldNotSendMailException
    {
        try
        {
            log.info("Generating mail for: {}", message);
            MimeMessage mimeMessage = this.mailCreator.create(message);
            if (mimeMessage != null)
            {
                log.info("Sending mail with subject: {}, to: {}", message.getSubject(), message.getTo());
                mailSender.send(mimeMessage);
            }
        }
        catch (Exception exception)
        {
            throw new CouldNotSendMailException(exception);
        }
    }
    public MailService(MailSender mailSender, @Qualifier("mailCreatorImpl") MailCreator mailCreator) {
        this.mailSender = mailSender;
        this.mailCreator = mailCreator;
    }
}
