package com.service.rentacar.rest.request;

import lombok.Value;

@Value
public class MfaAuthRequest
{
    String challenge;
    String code;
}
