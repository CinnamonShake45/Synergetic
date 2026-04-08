package com.cinnamonshake.storefront.controller;

import com.cinnamonshake.storefront.dto.DeviceResponse;
import com.cinnamonshake.storefront.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    // TEMP: replace with JWT later
    private final Long USER_ID = 1L;

    // 🔹 Get all devices of logged-in user
    @GetMapping
    public List<DeviceResponse> getUserDevices() {
        return deviceService.getUserDevices(USER_ID);
    }

    // 🔹 Get specific device by serial
    @GetMapping("/{serial}")
    public DeviceResponse getDevice(@PathVariable String serial) {
        return deviceService.getDeviceBySerial(serial);
    }
}