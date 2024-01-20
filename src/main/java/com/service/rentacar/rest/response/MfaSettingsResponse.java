package com.service.rentacar.rest.response;

import com.service.rentacar.application.security.mfa.MfaType;
import lombok.Value;

@Value(staticConstructor = "of")
public class MfaSettingsResponse
{
    MfaType mfaType;
    boolean verified;
    String verifiedDate;
}
