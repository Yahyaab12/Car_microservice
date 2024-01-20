package com.service.rentacar.application.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.RESERVATION_NOT_FOUND, status = HttpStatus.NOT_FOUND, messageKey = "reservation.error.not-found")
public class ReservationException extends RuntimeException
{

}
