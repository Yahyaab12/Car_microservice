package com.service.rentacar.application.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.BAD_CREDENTIALS, status = HttpStatus.BAD_REQUEST, messageKey = "auth.error.bad-credentials")
public class BadCredentialsException extends RuntimeException {

}
