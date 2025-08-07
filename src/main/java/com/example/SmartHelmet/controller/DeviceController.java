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

//    @GetMapping("/dashboard")
//    public List<Device> getDevices() {
//        System.out.println("디바이스 요청 들어옴!");
//
//        List<Device> devices = new ArrayList<>();
//
//        devices.add(createDevice("AICT-001", "AICT-001", 37.5665, 126.978, "정상", "온라인", "착용중", "2024-01-15 14:30:25"));
//        devices.add(createDevice("AICT-002", "AICT-002", 37.5651, 126.9895, "점검필요", "오프라인", "미착용", "2024-01-15 12:15:10"));
//        devices.add(createDevice("AICT-003", "AICT-003", 37.5707, 126.9772, "정상", "온라인", "착용중", "2024-01-15 14:28:15"));
////        devices.add(createDevice("AICT-004", "AICT-004", 37.5689, 126.9831, "비상", "온라인", "착용중", "2024-01-15 14:35:12"));
//
//        return devices;
//    }
//
//    private Device createDevice(String id, String name, Double latitude, Double longitude, String status, String powerStatus, String wearStatus, String lastUpdate) {
//        return Device.builder()
//                .id(id)
//                .name(name)
//                .lat(latitude)
//                .lng(longitude)
//                .status(status)
//                .powerStatus(powerStatus)
//                .wearStatus(wearStatus)
//                .lastUpdate(lastUpdate).build();
//    }
}
