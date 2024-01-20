package com.service.rentacar.application.exception;

import com.service.rentacar.domain.ApiException;
import com.service.rentacar.domain.ApiExceptionCode;
import org.springframework.http.HttpStatus;

@ApiException(code = ApiExceptionCode.RESERVATION_VEHICLE_NOT_AVAILABLE, status = HttpStatus.CONFLICT, messageKey = "reservation.error.vehicle-not-available")
public class ReservationVehicleNotAvailableException extends RuntimeException
{

}
