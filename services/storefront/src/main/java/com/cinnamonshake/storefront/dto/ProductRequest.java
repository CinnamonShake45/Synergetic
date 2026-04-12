package com.cinnamonshake.storefront.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private Double price;

    private Double capacity;
    private String batteryType;
    private Integer ratedCycles;

    private String description;
}