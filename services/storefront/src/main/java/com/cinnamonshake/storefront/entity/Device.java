package com.cinnamonshake.storefront.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serialNumber;

    private String status; // ACTIVE, INACTIVE

    private double capacity;
    private String batteryType;

    private LocalDateTime activatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}