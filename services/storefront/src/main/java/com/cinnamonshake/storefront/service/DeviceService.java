package com.cinnamonshake.storefront.service;

import com.cinnamonshake.storefront.dto.DeviceResponse;
import com.cinnamonshake.storefront.entity.Device;
import com.cinnamonshake.storefront.entity.User;
import com.cinnamonshake.storefront.repository.DeviceRepository;
import com.cinnamonshake.storefront.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    public List<DeviceResponse> getUserDevices(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return deviceRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public DeviceResponse getDeviceBySerial(String serial) {

        Device device = deviceRepository.findBySerialNumber(serial)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        return mapToResponse(device);
    }

    private DeviceResponse mapToResponse(Device device) {
        return DeviceResponse.builder()
                .serialNumber(device.getSerialNumber())
                .status(device.getStatus())
                .capacity(device.getCapacity())
                .batteryType(device.getBatteryType())
                .activatedAt(device.getActivatedAt())
                .build();
    }
}