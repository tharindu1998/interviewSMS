package com.treinetic.interviews1.service;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    private String isMaintain;

    public boolean isServerHealthy() {
        if (isMaintain.equals("yes")) {
            return true;
        }
        return false;
    }
}
