package com.service.rentacar.rest.response;

import com.service.rentacar.domain.reservation.dto.ProfileReservation;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class ReservationsResponse
{
    List<ProfileReservation> reservations;
}
