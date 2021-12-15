package com.treinetic.interviews1.controllers;

import com.treinetic.interviews1.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class HealthCheckController {

    private HealthCheckService healthCheckService;

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        if (this.healthCheckService.isServerHealthy()) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("NO", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PostConstruct
    public void createInstance() {
        this.healthCheckService = new HealthCheckService();
    }

}
