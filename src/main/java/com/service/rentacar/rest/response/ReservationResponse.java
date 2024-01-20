package com.service.rentacar.rest.response;

import com.service.rentacar.domain.reservation.dto.Reservation;
import lombok.Value;

@Value(staticConstructor = "of")
public class ReservationResponse
{
    Reservation reservation;
}
