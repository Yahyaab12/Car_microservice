package com.service.rentacar.rest.response;

import com.service.rentacar.domain.pickup.dto.VehiclePickupLocation;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class VehiclePickupLocationsResponse
{
    List<VehiclePickupLocation> locations;
}
