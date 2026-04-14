package com.cinnamonshake.bmssim.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxConfig {

    @Bean
    public InfluxDBClient influxDBClient() {
        String url = "http://localhost:8086";
        String token = "my-token".toCharArray().toString();
        String org = "my-org";
        String bucket = "bms";

        return InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
    }
}