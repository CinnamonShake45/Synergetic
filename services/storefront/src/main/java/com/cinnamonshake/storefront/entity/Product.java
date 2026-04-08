package com.cinnamonshake.storefront.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private double price;

    private double capacity;      // kWh
    private String batteryType;   // Li-ion, LFP
    private int ratedCycles;

    @Column(length = 1000)
    private String description;
}