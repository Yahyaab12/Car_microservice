package com.service.rentacar.rest.response;

import com.service.rentacar.domain.vehicle.dto.VehicleFullData;
import lombok.Value;

@Value(staticConstructor = "of")
public class VehicleFullDataResponse
{
    VehicleFullData vehicle;
}
