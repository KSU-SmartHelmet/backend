package com.example.SmartHelmet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@Document(collection = "users")
@NoArgsConstructor
public class User {
    private String userId;
    private String password;
    private String nickName;
}
