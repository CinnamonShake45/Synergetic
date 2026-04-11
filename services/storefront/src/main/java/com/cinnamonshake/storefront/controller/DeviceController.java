package com.cinnamonshake.storefront.controller;

import com.cinnamonshake.storefront.dto.DeviceResponse;
import com.cinnamonshake.storefront.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    // 🔹 Get all devices of logged-in user
    @GetMapping("/my")
    public List<DeviceResponse> getUserDevices(Authentication authentication) {
        String username = authentication.getName();
        return deviceService.getUserDevices(username);
    }

    // 🔹 Get specific device by serial
    @GetMapping("/{serial}")
    public DeviceResponse getDevice(@PathVariable String serial) {
        return deviceService.getDeviceBySerial(serial);
    }
}