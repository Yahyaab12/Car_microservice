package com.service.rentacar.application.mail.listener;

import com.service.rentacar.application.mail.MailMessage;
import com.service.rentacar.application.mail.MailService;
import com.service.rentacar.application.mail.exception.CouldNotSendMailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitSendMailListener
{

    public RabbitSendMailListener(MailService mailService) {
        this.mailService = mailService;
    }
    private final MailService mailService;

    public void process(MailMessage message)
    {
        try
        {
            mailService.generateAndSend(message);
        }
        catch (CouldNotSendMailException e)
        {
            //TODO: We should retry few times before giving up.
            log.error(e.getMessage(), e);
        }
    }
}
