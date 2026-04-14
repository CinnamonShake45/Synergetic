package com.cinnamonshake.bmssim.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteryState {

    private Double capacityAh;
    private Double maxVoltage;
    private Double minVoltage;

    private Double currentSoC;
    private Double currentVoltage;
    private Double current;
    private Double temperature;
}