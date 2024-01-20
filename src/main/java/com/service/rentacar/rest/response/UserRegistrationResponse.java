package com.service.rentacar.rest.response;

import lombok.Value;

import java.time.ZonedDateTime;

@Value(staticConstructor = "of")
public class UserRegistrationResponse
{
    String token;
    ZonedDateTime expirationDate;
}
