package com.service.rentacar.domain.pickup.converter;

import com.service.rentacar.domain.pickup.dto.VehiclePickupLocation;
import com.service.rentacar.domain.pickup.model.VehiclePickupLocationEntity;
import org.springframework.stereotype.Component;

@Component
public class VehiclePickuLocationConverter
{
    public VehiclePickupLocation toDto(VehiclePickupLocationEntity vehiclePickupLocationEntity)
    {
        if (vehiclePickupLocationEntity == null)
        {
            return null;
        }

        return VehiclePickupLocation.builder()
                .id(vehiclePickupLocationEntity.getId())
                .name(vehiclePickupLocationEntity.getName())
                .city(vehiclePickupLocationEntity.getCity())
                .latitude(vehiclePickupLocationEntity.getLatitude())
                .longitude(vehiclePickupLocationEntity.getLongitude())
                .build();
    }

    public VehiclePickupLocationEntity toEntity(VehiclePickupLocation vehiclePickupLocation)
    {
        if (vehiclePickupLocation == null)
        {
            return null;
        }

        VehiclePickupLocationEntity vehiclePickupLocationEntity = new VehiclePickupLocationEntity();
        vehiclePickupLocationEntity.setId(vehiclePickupLocation.getId());
        vehiclePickupLocationEntity.setName(vehiclePickupLocation.getName());
        vehiclePickupLocationEntity.setCity(vehiclePickupLocation.getCity());
        vehiclePickupLocationEntity.setLatitude(vehiclePickupLocation.getLatitude());
        vehiclePickupLocationEntity.setLongitude(vehiclePickupLocation.getLongitude());
        return vehiclePickupLocationEntity;
    }
}
