package com.example.SmartHelmet.repository;

import com.example.SmartHelmet.dto.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepository extends MongoRepository<Device, String> {
}