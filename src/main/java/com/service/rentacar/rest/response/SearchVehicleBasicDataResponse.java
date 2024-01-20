package com.service.rentacar.rest.response;

import com.service.rentacar.domain.vehicle.dto.AvailableVehiclesSearchResult;
import com.service.rentacar.domain.vehicle.dto.VehicleBasicData;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class SearchVehicleBasicDataResponse
{
    List<VehicleBasicData> vehicles;
    long totalElements;
    int totalPages;

    public static SearchVehicleBasicDataResponse of(AvailableVehiclesSearchResult result)
    {
        return of(result.getVehicles(), result.getTotalElements(), result.getTotalPages());
    }
}
