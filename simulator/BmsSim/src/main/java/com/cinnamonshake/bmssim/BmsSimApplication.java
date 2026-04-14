package com.cinnamonshake.bmssim;

import com.cinnamonshake.bmssim.service.BMSEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class BmsSimApplication {

    private final BMSEngine engine;

    public BmsSimApplication(BMSEngine engine) {
        this.engine = engine;
    }

    public static void main(String[] args) {
        SpringApplication.run(BmsSimApplication.class, args);
    }

}
