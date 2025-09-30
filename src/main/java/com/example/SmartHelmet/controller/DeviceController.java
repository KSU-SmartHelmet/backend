package com.example.SmartHelmet.controller;

import com.example.SmartHelmet.dto.Device;
import com.example.SmartHelmet.service.DeviceService;
import com.example.SmartHelmet.websocket.DeviceEventPublisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceEventPublisher deviceEventPublisher;

    @GetMapping("/dashboard")
    public List<Device> getDevices() {
        return deviceService.getAllDevices();
    }

    @PostMapping("/dashboard/update")
    public Device updateDevice(@RequestBody Device device) {
        Device updated = deviceService.updateDevice(device);
        deviceEventPublisher.sendDeviceUpdate(updated);
        return updated;
    }
}
