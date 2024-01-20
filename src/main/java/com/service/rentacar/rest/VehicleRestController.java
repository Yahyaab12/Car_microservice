package com.service.rentacar.rest;

import com.service.rentacar.domain.vehicle.VehicleService;
import com.service.rentacar.domain.vehicle.dto.AvailableVehiclesSearchParams;
import com.service.rentacar.domain.vehicle.dto.VehicleFullData;
import com.service.rentacar.rest.response.SearchVehicleBasicDataResponse;
import com.service.rentacar.rest.response.VehicleAvailabilityResponse;
import com.service.rentacar.rest.response.VehicleBasicDataResponse;
import com.service.rentacar.rest.response.VehicleFullDataResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@AllArgsConstructor
public class VehicleRestController {

    private final VehicleService vehicleService;

    @GetMapping("/full-data/{vehicleId}")
    public ResponseEntity<VehicleFullDataResponse> getVehicleFullData(@PathVariable Integer vehicleId) {
        return ResponseEntity.ok(VehicleFullDataResponse.of(this.vehicleService.getVehicleFullData(vehicleId)));
    }

    @GetMapping("/available")
    public ResponseEntity<SearchVehicleBasicDataResponse> getVehiclesAvailable(@RequestParam("dateFrom") LocalDate dateFrom,
                                                                               @RequestParam("dateTo") LocalDate dateTo,
                                                                               @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                               @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(SearchVehicleBasicDataResponse.of(this.vehicleService.getVehiclesAvailable(AvailableVehiclesSearchParams.of(dateFrom, dateTo, page, size))));
    }

    @GetMapping
    public ResponseEntity<VehicleBasicDataResponse> getAllVehicles() {
        return ResponseEntity.ok(VehicleBasicDataResponse.of(this.vehicleService.getAllVehicles()));
    }

    @GetMapping("/{vehicleId}/available")
    public ResponseEntity<VehicleAvailabilityResponse> isVehicleAvailable(@RequestParam("dateFrom") LocalDate dateFrom,
                                                                          @RequestParam("dateTo") LocalDate dateTo,
                                                                          @PathVariable("vehicleId") int vehicleId)
    {
        return ResponseEntity.ok(VehicleAvailabilityResponse.of(this.vehicleService.isVehicleAvailable(vehicleId, dateFrom, dateTo)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_VEHICLE')")
    public ResponseEntity<VehicleFullDataResponse> saveVehicle(@RequestPart(value = "images")List<MultipartFile> images, @RequestPart(value = "vehicle") VehicleFullData vehicle) {
        return ResponseEntity.ok(VehicleFullDataResponse.of(this.vehicleService.saveVehicleWithImages(vehicle, images)));
    }

    @DeleteMapping("/{vehicleId}")
    @PreAuthorize("hasAuthority('REMOVE_VEHICLE')")
    public ResponseEntity<?> removeVehicle(@PathVariable int vehicleId) {
        this.vehicleService.removeVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }
}
