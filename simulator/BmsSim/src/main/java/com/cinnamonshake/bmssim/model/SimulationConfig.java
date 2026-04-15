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

    @Builder.Default
    private Double loadCurrent = 1.0;
    @Builder.Default
    private Double chargeCurrent = 1.0;
    @Builder.Default
    private Boolean charging = false;
    @Builder.Default
    private Long intervalMs = 1500L;

    public boolean isCharging() {
        return getCharging();
    }
}