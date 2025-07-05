package com.whispr.messenger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class DummyController {

    @RequestMapping("/dummy")
    public String dummyEndpoint() {
        return "Dummy endpoint is working!";
    }
}
