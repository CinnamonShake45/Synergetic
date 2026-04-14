package com.cinnamonshake.bmssim.model;

import lombok.Data;

@Data
public class BatteryInitRequest {

    private Double capacityAh;
    private Double maxVoltage;
    private Double minVoltage;
    private Double initialSoC;
}