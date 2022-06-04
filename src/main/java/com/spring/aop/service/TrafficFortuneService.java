package com.spring.aop.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneService {

    public String getFortune(Boolean flag) {
        if (flag) {
            throw new RuntimeException("Major accident");
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Heavy traffic expected";
    }
}
