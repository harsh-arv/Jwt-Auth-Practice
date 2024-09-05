package com.jwt.authentication.service;

import org.springframework.stereotype.Service;

@Service
public class MySecuredService {

    public String getSecretData() {
        return "This is sensitive data!";
    }
}