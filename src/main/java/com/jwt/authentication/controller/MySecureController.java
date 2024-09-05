package com.jwt.authentication.controller;

import com.jwt.authentication.service.MySecuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured")
public class MySecureController {

    @Autowired
    private MySecuredService mySecuredService;

    @GetMapping("/data")
    public String getSecuredData() {
        return mySecuredService.getSecretData();
    }
}
