package com.tdd.greetingsapp.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreetingByGender(String gender) {
        return "male".equals(gender)?"Mr":"Miss";
    }
}
