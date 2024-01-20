package com.service.rentacar.application.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.USER_NOT_EXIST, status = HttpStatus.BAD_REQUEST, messageKey = "auth.error.user-not-exist")
public class UserDoesNotExistException extends RuntimeException
{

}
