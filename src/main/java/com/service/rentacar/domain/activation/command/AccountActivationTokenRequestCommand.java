package com.service.rentacar.domain.activation.command;

import com.service.rentacar.application.lang.LangCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountActivationTokenRequestCommand
{
    private Long credentialsId;
    private String emailTo;
    private LangCode langCode;
}
