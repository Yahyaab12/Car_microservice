package com.service.rentacar.application.security.mfa.dto;

import com.service.rentacar.application.security.mfa.MfaType;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UserMfaSettings
{
    private Long id;
    private Long credentialsId;
    private MfaType mfaType;
    private boolean verified;
    private ZonedDateTime verifiedDate;
}
