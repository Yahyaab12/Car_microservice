package com.service.rentacar.domain.activation;

import com.service.rentacar.application.config.RabbitMessageSender;
import com.service.rentacar.application.exception.MessageSendException;
import com.service.rentacar.application.mail.MailMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ActivationLinkMailSender
{
    private final RabbitMessageSender rabbitMessageSender;

    public void send(MailMessage message)
    {
        try
        {
            rabbitMessageSender.send("mail.send", message);
        }
        catch (Exception e)
        {
            //TODO: We should retry...
            log.error("Could not send message {}", message, e);
        }
    }
}
