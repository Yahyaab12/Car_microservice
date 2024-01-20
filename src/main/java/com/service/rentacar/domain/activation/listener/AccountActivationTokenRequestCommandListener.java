package com.service.rentacar.domain.activation.listener;

import com.service.rentacar.application.mail.MailMessage;
import com.service.rentacar.application.mail.MailType;
import com.service.rentacar.domain.activation.AccountActivationService;
import com.service.rentacar.domain.activation.ActivationLinkMailSender;
import com.service.rentacar.domain.activation.command.AccountActivationTokenRequestCommand;
import com.service.rentacar.domain.activation.model.ActivationTokenEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountActivationTokenRequestCommandListener
{
    private final AccountActivationService accountActivationService;
    private final ActivationLinkMailSender activationLinkMailSender;

    @RabbitListener(queues = "account.activation.request")
    public void process(AccountActivationTokenRequestCommand command)
    {
        log.info("Processing command: {}", command);

        ActivationTokenEntity activationTokenEntity = accountActivationService.invalidateOldActivationTokensAndGenerateNew(command.getCredentialsId());

        this.activationLinkMailSender.send(MailMessage.builder()
                        .to(command.getEmailTo())
                        .subject("Account Activation")
                        .type(MailType.ACCOUNT_ACTIVATION)
                        .langCode(command.getLangCode())
                        .properties(Map.of("activation_token", activationTokenEntity.getToken()))
                .build());
    }
}
