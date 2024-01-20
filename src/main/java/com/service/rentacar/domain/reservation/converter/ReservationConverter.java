package com.service.rentacar.domain.reservation.converter;

import com.service.rentacar.domain.profile.model.UserProfileEntity;
import com.service.rentacar.domain.reservation.ReservationImagePopulator;
import com.service.rentacar.domain.reservation.dto.ProfileReservation;
import com.service.rentacar.domain.reservation.dto.Reservation;
import com.service.rentacar.domain.reservation.model.ReservationEntity;
import com.service.rentacar.domain.vehicle.VehicleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationConverter {

    private final ReservationImagePopulator imagePopulator;

    public Reservation toReservationDto(ReservationEntity reservationEntity) {
        if (reservationEntity == null) {
            return null;
        }

        return Reservation.builder()
                .id(reservationEntity.getId())
                .vehicleId(reservationEntity.getVehicle().getId())
                .userId(reservationEntity.getUserProfile().getId())
                .dateFrom(reservationEntity.getDateFrom())
                .dateTo(reservationEntity.getDateTo())
                .status(reservationEntity.getStatus())
                .build();
    }

    public ReservationEntity toReservationEntity(Reservation reservationDto) {
        if (reservationDto == null) {
            return null;
        }

        return ReservationEntity.builder()
                .id(reservationDto.getId())
                .vehicle(VehicleEntity.builder()
                        .id(reservationDto.getVehicleId())
                        .build())
                .userProfile(UserProfileEntity.builder()
                        .id(reservationDto.getUserId())
                        .build())
                .dateFrom(reservationDto.getDateFrom())
                .dateTo(reservationDto.getDateTo())
                .status(reservationDto.getStatus())
                .build();
    }


    public ProfileReservation toProfileReservation(ReservationEntity reservationEntity) {
        if (reservationEntity == null) {
            return null;
        }

        ProfileReservation.ProfileReservationBuilder builder = ProfileReservation.builder()
                .id(reservationEntity.getId())
                .vehicleName(reservationEntity.getVehicle().getBrand() + " " + reservationEntity.getVehicle().getModel())
                .dateFrom(reservationEntity.getDateFrom())
                .dateTo(reservationEntity.getDateTo())
                .status(reservationEntity.getStatus());
        imagePopulator.populate(builder, reservationEntity.getVehicle().getPhotoNames().get(0));
        return builder.build();
    }
}
