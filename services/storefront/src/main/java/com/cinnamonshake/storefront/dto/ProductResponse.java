package com.cinnamonshake.storefront.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private double price;

    private double capacity;
    private String batteryType;
    private int ratedCycles;

    private String description;
}