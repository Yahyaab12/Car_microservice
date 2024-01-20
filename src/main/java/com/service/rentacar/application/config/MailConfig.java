package com.service.rentacar.application.config;

import com.service.rentacar.application.mail.MailCreator;
import com.service.rentacar.application.mail.MailCreatorImpl;
import com.service.rentacar.application.mail.MailSender;
import com.service.rentacar.application.mail.RentaCarMailSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig
{
    @Bean
    @ConditionalOnProperty(value = "rentacar.mail-sender.enabled", havingValue = "true")
    public MailSender rentaCarMailSender(JavaMailSender javaMailSender)
    {
        return new RentaCarMailSender(javaMailSender);
    }

    @Bean
    @ConditionalOnProperty(value = "rentacar.mail-sender.enabled", matchIfMissing = true, havingValue = "false")
    public MailSender noOpMailSender()
    {
        return new MailSender.NoOpMailSender();
    }

    @Bean
    @ConditionalOnProperty(value = "rentacar.mail-sender.enabled", havingValue = "true")
    public MailCreator rentacarMailCreator(JavaMailSender javaMailSender)
    {
        return new MailCreatorImpl(javaMailSender);
    }

    @Bean
    @ConditionalOnProperty(value = "rentacar.mail-sender.enabled", matchIfMissing = true, havingValue = "false")
    public MailCreator noOpMailCreator()
    {
        return new MailCreator.NoOpMailCreator();
    }
}
