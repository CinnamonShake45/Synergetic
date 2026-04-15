package com.cinnamonshake.bmssim.controller;

import com.cinnamonshake.bmssim.model.BatteryState;
import com.cinnamonshake.bmssim.model.SimulationLoadConfig;
import com.cinnamonshake.bmssim.service.BMSEngine;
import com.cinnamonshake.bmssim.model.BatteryInitRequest;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bms")
public class BMSController {

    private final BMSEngine engine;

    public BMSController(BMSEngine engine) {
        this.engine = engine;
    }

    @PostMapping("/start")
    public String start(@Valid @RequestBody(required = false) BatteryInitRequest init) {
        engine.start(init);
        return "BMS Started";
    }

    @PostMapping("/stop")
    public String stop() {
        engine.stop();
        return "BMS Stopped";
    }

    @PostMapping("/reset")
    public String reset() {
        engine.reset();
        return "BMS Reset";
    }

    @PostMapping("/pause")
    public String pause() {
        engine.pause();
        return "Paused";
    }

    @PostMapping("/resume")
    public String resume() {
        engine.resume();
        return "Resumed";
    }

    @PostMapping("/load/update")
    public String updateLoadConfig(@RequestBody SimulationLoadConfig config) {

        if (config.getLoadCurrent() != null) {
            engine.getConfig().setLoadCurrent(config.getLoadCurrent());
        }

        if (config.getChargeCurrent() != null) {
            engine.getConfig().setChargeCurrent(config.getChargeCurrent());
        }

        if (config.getCharging() != null) {
            engine.getConfig().setCharging(config.getCharging());
        }

        if (config.getIntervalMs() != null) {
            engine.getConfig().setIntervalMs(config.getIntervalMs());
        }

        return "Load configuration updated";
    }

    @GetMapping("/battery/state")
    public BatteryState getState() {
        return engine.getBattery();
    }

    @GetMapping("/load/state")
    public SimulationLoadConfig getLoad() {
        return engine.getConfig();
    }

}