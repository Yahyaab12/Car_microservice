package com.service.rentacar.domain.vehicle.converter;

import com.service.rentacar.domain.vehicle.VehicleEntity;
import com.service.rentacar.domain.vehicle.VehicleImagePopulator;
import com.service.rentacar.domain.vehicle.dto.VehicleBasicData;
import com.service.rentacar.domain.vehicle.dto.VehicleFullData;
import com.service.rentacar.domain.vehicle.enums.VehicleCategory;
import com.service.rentacar.domain.vehicle.enums.VehicleEngine;
import com.service.rentacar.domain.vehicle.enums.VehicleTransmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleConverter {

    private final VehicleImagePopulator vehicleImagePopulator;

    public VehicleBasicData toBasicData(VehicleEntity vehicleEntity) {
        if (vehicleEntity == null) {
            return null;
        }

        VehicleBasicData.VehicleBasicDataBuilder builder = VehicleBasicData.builder()
                .id(vehicleEntity.getId())
                .brand(vehicleEntity.getBrand())
                .model(vehicleEntity.getModel())
                .engine(VehicleBasicData.Engine.builder()
                        .type(VehicleEngine.valueOf(vehicleEntity.getEngineType()))
                        .avgFuelConsumption(vehicleEntity.getAvgFuelConsumption())
                        .build())
                .equipment(VehicleBasicData.Equipment.builder()
                        .ac(vehicleEntity.isAc())
                        .ledFrontLights(vehicleEntity.isLedFrontLights())
                        .ledRearLights(vehicleEntity.isLedRearLights())
                        .leatherSeats(vehicleEntity.isLeatherSeats())
                        .multifunctionalSteeringWheel(vehicleEntity.isMultifunctionalSteeringWheel())
                        .build())
                .basicPrice(vehicleEntity.getBasicPrice());

        vehicleImagePopulator.populate(builder, vehicleEntity.getPhotoNames());

        return builder.build();
    }

    public VehicleFullData toFullData(VehicleEntity vehicleEntity) {
        if (vehicleEntity == null) {
            return null;
        }

        VehicleFullData.VehicleFullDataBuilder builder = VehicleFullData.builder()
                .id(vehicleEntity.getId())
                .brand(vehicleEntity.getBrand())
                .model(vehicleEntity.getModel())
                .productionYear(vehicleEntity.getProductionYear())
                .seatsAmount(vehicleEntity.getSeatsAmount())
                .body(VehicleFullData.Body.builder()
                        .color(vehicleEntity.getColor())
                        .rimsInch(vehicleEntity.getRimsInch())
                        .build())
                .engine(VehicleFullData.Engine.builder()
                        .capacity(vehicleEntity.getCapacity())
                        .type(VehicleEngine.valueOf(vehicleEntity.getEngineType()))
                        .power(vehicleEntity.getPower())
                        .torque(vehicleEntity.getTorque())
                        .avgFuelConsumption(vehicleEntity.getAvgFuelConsumption())
                        .transmission(VehicleTransmission.valueOf(vehicleEntity.getTransmission()))
                        .build())
                .equipment(VehicleFullData.Equipment.builder()
                        .ac(vehicleEntity.isAc())
                        .frontPDC(vehicleEntity.isFrontPDC())
                        .rearPDC(vehicleEntity.isRearPDC())
                        .ledFrontLights(vehicleEntity.isLedFrontLights())
                        .ledRearLights(vehicleEntity.isLedRearLights())
                        .xenonFrontLights(vehicleEntity.isXenonFrontLights())
                        .bluetooth(vehicleEntity.isBluetooth())
                        .leatherSeats(vehicleEntity.isLeatherSeats())
                        .multifunctionalSteeringWheel(vehicleEntity.isMultifunctionalSteeringWheel())
                        .cruiseControl(vehicleEntity.isCruiseControl())
                        .build())
                .basicPrice(vehicleEntity.getBasicPrice())
                .category(VehicleCategory.valueOf(vehicleEntity.getCategory()));

        vehicleImagePopulator.populate(builder, vehicleEntity.getPhotoNames());

        return builder.build();
    }

    public VehicleEntity fullDataToEntity(VehicleFullData vehicleFullData) {
        if (vehicleFullData == null) {
            return null;
        }

        return VehicleEntity.builder()
                .id(vehicleFullData.getId())
                .brand(vehicleFullData.getBrand())
                .model(vehicleFullData.getModel())
                .productionYear(vehicleFullData.getProductionYear())
                .seatsAmount(vehicleFullData.getSeatsAmount())
                .color(vehicleFullData.getBody().getColor())
                .rimsInch(vehicleFullData.getBody().getRimsInch())
                .capacity(vehicleFullData.getEngine().getCapacity())
                .engineType(vehicleFullData.getEngine().getType().toString())
                .power(vehicleFullData.getEngine().getPower())
                .torque(vehicleFullData.getEngine().getTorque())
                .avgFuelConsumption(vehicleFullData.getEngine().getAvgFuelConsumption())
                .transmission(vehicleFullData.getEngine().getTransmission().toString())
                .ac(vehicleFullData.getEquipment().isAc())
                .frontPDC(vehicleFullData.getEquipment().isFrontPDC())
                .rearPDC(vehicleFullData.getEquipment().isRearPDC())
                .ledFrontLights(vehicleFullData.getEquipment().isLedFrontLights())
                .xenonFrontLights(vehicleFullData.getEquipment().isXenonFrontLights())
                .ledRearLights(vehicleFullData.getEquipment().isLedRearLights())
                .bluetooth(vehicleFullData.getEquipment().isBluetooth())
                .leatherSeats(vehicleFullData.getEquipment().isLeatherSeats())
                .multifunctionalSteeringWheel(vehicleFullData.getEquipment().isMultifunctionalSteeringWheel())
                .cruiseControl(vehicleFullData.getEquipment().isCruiseControl())
                .basicPrice(vehicleFullData.getBasicPrice())
                .category(vehicleFullData.getCategory().toString())
                .build();
    }
}
