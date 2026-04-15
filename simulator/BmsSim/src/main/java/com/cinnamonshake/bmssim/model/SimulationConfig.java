package com.cinnamonshake.bmssim.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationConfig {

    private Double loadCurrent = 1.0;
    private Double chargeCurrent = 1.0;
    private Boolean charging = false;
    private Long intervalMs = 1500L;

    public boolean isCharging() {
        return getCharging();
    }
}