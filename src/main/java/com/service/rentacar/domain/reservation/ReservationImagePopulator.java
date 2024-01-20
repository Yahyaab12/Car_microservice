package com.service.rentacar.domain.reservation;

import com.service.rentacar.domain.image.ImageService;
import com.service.rentacar.domain.image.model.ImageKind;
import com.service.rentacar.domain.reservation.dto.ProfileReservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationImagePopulator
{
    private final ImageService imageService;

    public void populate(ProfileReservation.ProfileReservationBuilder builder, String imageName)
    {
        builder.vehicleIconUrl(imageService.getImageUri(ImageKind.VEHICLE, imageName).getUri().toString());
    }
}
