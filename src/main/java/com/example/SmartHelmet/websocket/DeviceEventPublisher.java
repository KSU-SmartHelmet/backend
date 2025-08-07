package com.example.SmartHelmet.websocket;

import com.example.SmartHelmet.dto.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendDeviceUpdate(Device device) {
        messagingTemplate.convertAndSend("/topic/deviceUpdate", device);
    }
}
