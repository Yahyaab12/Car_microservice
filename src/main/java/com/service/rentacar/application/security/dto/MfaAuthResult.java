package com.service.rentacar.application.security.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class MfaAuthResult
{
    boolean success;
}
