package com.service.rentacar.domain.user.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.USER_EXISTS, status = HttpStatus.CONFLICT, messageKey = "user.error.username-email-already-used")
public class UsernameOrEmailAlreadyUsedException extends RuntimeException
{

}
