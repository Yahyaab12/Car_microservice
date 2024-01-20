package com.service.rentacar.domain.pickup.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VehiclePickupLocation
{
    Integer id;
    String name;
    String city;
    float latitude;
    float longitude;
}
