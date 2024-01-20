package com.service.rentacar.rest.response;

import com.service.rentacar.domain.vehicle.dto.VehicleBasicData;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class VehicleBasicDataResponse
{
   List<VehicleBasicData> vehicles;
}
