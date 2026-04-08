package com.cinnamonshake.storefront.repository;

import com.cinnamonshake.storefront.entity.Device;
import com.cinnamonshake.storefront.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByUser(User user);

    Optional<Device> findBySerialNumber(String serialNumber);
}