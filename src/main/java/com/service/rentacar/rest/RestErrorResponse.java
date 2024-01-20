package com.service.rentacar.rest;

import lombok.Value;

@Value(staticConstructor = "of")
public class RestErrorResponse {
    String code;
    String message;
}
