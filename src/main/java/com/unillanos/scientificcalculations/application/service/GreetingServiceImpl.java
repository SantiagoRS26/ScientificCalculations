package com.unillanos.scientificcalculations.application.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet() {
        return "Hello World";
    }
}
