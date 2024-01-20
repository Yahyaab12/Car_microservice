package com.service.rentacar.application.security.dto;

import lombok.Value;

import java.util.Set;

@Value(staticConstructor = "of")
public class MfaActivationResult
{
    Set<String> recoveryCodes;
}
