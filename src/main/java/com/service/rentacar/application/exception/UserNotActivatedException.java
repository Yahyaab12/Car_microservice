package com.service.rentacar.application.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.USER_NOT_ACTIVATED, status = HttpStatus.FORBIDDEN, messageKey = "auth.error.user-not-activated")
public class UserNotActivatedException extends RuntimeException
{

}
