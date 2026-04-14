package com.cinnamonshake.bmssim.controller;

import com.cinnamonshake.bmssim.model.SimulationConfig;
import com.cinnamonshake.bmssim.service.BMSEngine;
import com.cinnamonshake.bmssim.model.BatteryInitRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bms")
public class BMSController {

    private final BMSEngine engine;

    public BMSController(BMSEngine engine) {
        this.engine = engine;
    }

    @PostMapping("/start")
    public String start(@RequestBody(required = false) BatteryInitRequest init) {
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

    @PostMapping("/config")
    public String updateConfig(@RequestBody SimulationConfig config) {

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

        return "Updated";
    }
}