package com.service.rentacar.application.security.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(
        status = HttpStatus.BAD_REQUEST,
        code = ApiExceptionCode.MFA_ALREADY_ACTIVATED,
        messageKey = "user.error.mfa-already-activated"
)
public class MfaAlreadyActivatedException extends RuntimeException
{

}
