package com.service.rentacar.domain.activation.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.ACTIVATION_TOKEN_EXPIRED, status = HttpStatus.BAD_REQUEST, messageKey = "activation-token.error.expired")
public class ActivationTokenExpiredException extends RuntimeException
{
}
