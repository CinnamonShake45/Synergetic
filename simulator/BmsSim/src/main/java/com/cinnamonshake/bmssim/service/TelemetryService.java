package com.cinnamonshake.bmssim.service;

import com.cinnamonshake.bmssim.model.BatteryState;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.write.Point;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TelemetryService {

    private final WriteApiBlocking writeApi;

    public TelemetryService(InfluxDBClient influxDBClient) {
        this.writeApi = influxDBClient.getWriteApiBlocking();
    }

    public void write(BatteryState state) {

        Point point = Point.measurement("battery")
                .addField("soc", state.getCurrentSoC())
                .addField("voltage", state.getCurrentVoltage())
                .addField("current", state.getCurrent())
                .addField("temperature", state.getTemperature())
                .time(Instant.now(), com.influxdb.client.domain.WritePrecision.MS);

        writeApi.writePoint(point);
    }
}