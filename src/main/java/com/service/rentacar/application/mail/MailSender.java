package com.service.rentacar.application.mail;

import com.service.rentacar.application.mail.exception.CouldNotSendMailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public interface MailSender
{

    void send(MimeMessage mimeMessage) throws CouldNotSendMailException;

    /**
     * Used only in development/test environment.
     */
    @Deprecated
    @Slf4j
    class NoOpMailSender implements MailSender
    {

        @Override
        public void send(MimeMessage mimeMessage) throws CouldNotSendMailException
        {
            try
            {
                log.info("Sent mail: recipients={}, content={}", Arrays.toString(mimeMessage.getAllRecipients()), mimeMessage.getContent());
            }
            catch (MessagingException | IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
