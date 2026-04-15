package com.cinnamonshake.bmssim.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@ControllerAdvice
public class BatteryInitRequest {

    @NotBlank(message = "bmsID is required")
    @Pattern(
            regexp = "^BMS-[A-F0-9]{8}$",
            message = "bmsID must match format BMS-XXXXXXXX (hex)"
    )
    private String bmsID;
    private Double capacityAh;
    private Double maxVoltage;
    private Double minVoltage;
    private Double initialSoC;
}