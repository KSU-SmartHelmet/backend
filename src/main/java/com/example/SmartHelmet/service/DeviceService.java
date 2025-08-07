package com.example.SmartHelmet.service;


import com.example.SmartHelmet.dto.Device;
import com.example.SmartHelmet.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device updateDevice(Device updatedDevice) {
        return deviceRepository.save(updatedDevice); // save: insert or update
    }
}
