package org.whispr.socialmedia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/demo")
public class DemoController {

    @GetMapping
    public String hello() {
        return "Hello, World!";
    }
}
