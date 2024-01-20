package com.service.rentacar.domain.profile.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.PROFILE_NOT_FOUND, status = HttpStatus.NOT_FOUND, messageKey = "profile.error.not-found")
public class ProfileNotFoundException extends RuntimeException
{

}
