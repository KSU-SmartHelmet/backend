package com.example.SmartHelmet.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {
    public static final Long DEFAULT_TIMEOUT = 1000L * 3600; // 1h
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe() {
        String clientId = UUID.randomUUID().toString();
        SseEmitter emitter = createEmitter(clientId);
        sendInitialConnectionMessage(clientId);
        return emitter;
    }

    public void sendToAllClients(String eventName, Object data) {
        emitters.forEach((id, emitter) -> {
            try {
                emitter.send(SseEmitter.event().name(eventName).data(data, MediaType.APPLICATION_JSON));
            } catch (IOException e) {
                emitters.remove(id);
            }
        });
    }

    private SseEmitter createEmitter(String clientId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitters.put(clientId, emitter);

        emitter.onCompletion(() -> emitters.remove(clientId));
        emitter.onTimeout(emitter::complete);
        emitter.onError(e -> emitters.remove(clientId));

        return emitter;
    }

    private void sendInitialConnectionMessage(String clientId) {
        try {
            Map<String, String> connectMessage = Map.of(
                    "message", "SSE connected successfully.",
                    "clientId", clientId
            );
            emitters.get(clientId).send(SseEmitter.event().name("connect").data(connectMessage, MediaType.APPLICATION_JSON));
        } catch (IOException e) {
            emitters.remove(clientId);
        }
    }
}