package com.example.SmartHelmet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello World");
    }
}
