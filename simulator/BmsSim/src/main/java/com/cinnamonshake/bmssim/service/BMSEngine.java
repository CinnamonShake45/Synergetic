package com.cinnamonshake.bmssim.service;

import com.cinnamonshake.bmssim.model.BatteryState;
import com.cinnamonshake.bmssim.model.SimulationConfig;
import com.cinnamonshake.bmssim.model.BatteryInitRequest;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class BMSEngine {

    private BatteryState battery = null;
    @Getter
    private final SimulationConfig config = new SimulationConfig();

    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean paused = new AtomicBoolean(false);

    private final Random random = new Random();
    private Thread workerThread;

    public BMSEngine() {
    }

    // ===== Lifecycle =====

    public synchronized void start(BatteryInitRequest init) {

        if (running.get()) {
            System.out.println("BMS already running");
            return;
        }

        if (this.battery == null) {

            if( init == null){
                throw new IllegalStateException("Battery not initialized. Provide init data");
            }

            this.battery = BatteryState.builder()
                    .bmsId(init.getBmsId())
                    .capacityAh(init.getCapacityAh() != null ? init.getCapacityAh() : 2.5)
                    .maxVoltage(init.getMaxVoltage() != null ? init.getMaxVoltage() : 4.2)
                    .minVoltage(init.getMinVoltage() != null ? init.getMinVoltage() : 3.0)
                    .currentSoC(init.getInitialSoC() != null ? init.getInitialSoC() : 50.0)
                    .build();

            System.out.println("Battery initialized: " + battery.getBmsId() + " " + battery);
        } else {
            System.out.println("Resuming BMS: " + battery.getBmsId());
        }

        running.set(true);
        paused.set(false);

        workerThread = new Thread(this::loop, "bms-simulator-thread");
        workerThread.start();

        System.out.println("BMS started");
    }

    public synchronized void stop() {
        if (!running.get()) {
            System.out.println("BMS already stopped");
            return;
        }

        running.set(false);

        if (workerThread != null) {
            workerThread.interrupt();
        }

        System.out.println("BMS stopped");
    }

    public void pause() {
        paused.set(true);
    }

    public void resume() {
        paused.set(false);
    }

    private void loop() {
        while (running.get()) {

            if (!paused.get()) {
                updateBattery();
                printTelemetry();
            }

            try {
                Thread.sleep(config.getIntervalMs());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // ===== Core Simulation =====

    private void updateBattery() {

        double current = config.isCharging()
                ? config.getChargeCurrent()
                : -config.getLoadCurrent();

        double deltaAh = current / 3600.0;
        double socChange = (deltaAh / battery.getCapacityAh()) * 100;

        battery.setCurrentSoC(
                Math.clamp(battery.getCurrentSoC() + socChange, 0, 100)
        );

        double voltage = calculateVoltage(battery.getCurrentSoC());

        voltage = noise(voltage, 0.01);
        current = noise(current, 0.02);

        battery.setCurrentVoltage(voltage);
        battery.setCurrent(current);

        double temp = 25 + Math.abs(current) * 2;
        battery.setTemperature(noise(temp, 0.02));
    }

    private double calculateVoltage(double soc) {

        double minV = battery.getMinVoltage();
        double maxV = battery.getMaxVoltage();

        double normalized = soc / 100.0;
        double curve;

        if (normalized > 0.8) {
            curve = 0.9 + (normalized - 0.8) * 0.5;
        } else if (normalized > 0.2) {
            curve = 0.5 + (normalized - 0.2) * 0.5;
        } else {
            curve = normalized * 0.5;
        }

        return minV + curve * (maxV - minV);
    }

    private double noise(double value, double percentage) {
        double variation = value * percentage;
        return value + (random.nextDouble() * 2 - 1) * variation;
    }

    private void printTelemetry() {
        System.out.printf(
                "[%s] SOC: %.2f%% | Voltage: %.3fV | Current: %.3fA | Temp: %.2f°C%n",
                LocalTime.now(),
                battery.getCurrentSoC(),
                battery.getCurrentVoltage(),
                battery.getCurrent(),
                battery.getTemperature()
        );
    }

    public synchronized void reset() {

        // Stop if running
        if (running.get()) {
            stop();
        }
        this.battery = null;

        // Reset config
        config.setLoadCurrent(1.0);
        config.setChargeCurrent(1.0);
        config.setCharging(false);
        config.setIntervalMs(1500L);

        paused.set(false);

        System.out.println("BMS reset to default state");
    }
}