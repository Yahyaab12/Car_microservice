package com.service.rentacar.rest.request;

import lombok.Value;

@Value
public class UserRegistrationRequest
{
    String username;
    String email;
    String password;
}
