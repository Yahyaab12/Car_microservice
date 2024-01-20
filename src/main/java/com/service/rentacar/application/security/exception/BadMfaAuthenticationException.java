package com.service.rentacar.application.security.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(
        status = HttpStatus.BAD_REQUEST,
        code = ApiExceptionCode.BAD_CREDENTIALS,
        messageKey = "user.error.bad-mfa-authentication")
public class BadMfaAuthenticationException extends RuntimeException
{

}
