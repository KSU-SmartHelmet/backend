package com.example.SmartHelmet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@Builder
@Document(collection = "devices")
//@NoArgsConstructor
public class Device {
    private String id;
    private String name;
    private double lat;
    private double lng;
    private String status;
    private String powerStatus;
    private String wearStatus;
    private String lastUpdate;
}