package com.cinnamonshake.storefront.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DeviceResponse {

    private String serialNumber;
    private String status;

    private double capacity;
    private String batteryType;

    private LocalDateTime activatedAt;
}