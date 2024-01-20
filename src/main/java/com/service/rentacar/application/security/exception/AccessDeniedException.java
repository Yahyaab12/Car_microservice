package com.service.rentacar.application.security.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.ACCESS_DENIED, status = HttpStatus.FORBIDDEN, messageKey = "error.access-denied")
public class AccessDeniedException extends RuntimeException
{

}
