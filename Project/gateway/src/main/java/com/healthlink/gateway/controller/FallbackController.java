package com.healthlink.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/fallback")
public class FallbackController {

//    @RequestMapping("/patient")
//    public Mono<ResponseEntity<Map<String, Object>>> patientFallback() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Patient service is temporarily unavailable");
//        response.put("status", "SERVICE_UNAVAILABLE");
//        response.put("timestamp", LocalDateTime.now());
//        response.put("service", "patient");
//
//        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response));
//    }
//
//    @RequestMapping("/encounter")
//    public Mono<ResponseEntity<Map<String, Object>>> encounterFallback() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Encounter service is temporarily unavailable");
//        response.put("status", "SERVICE_UNAVAILABLE");
//        response.put("timestamp", LocalDateTime.now());
//        response.put("service", "encounter");
//
//        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response));
//    }
//
//    @RequestMapping("/observation")
//    public Mono<ResponseEntity<Map<String, Object>>> observationFallback() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Observation service is temporarily unavailable");
//        response.put("status", "SERVICE_UNAVAILABLE");
//        response.put("timestamp", LocalDateTime.now());
//        response.put("service", "observation");
//
//        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response));
//    }

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("An error occurred. Please try after some time or contact support team!!!");
    }
}
