package com.whispr.prototype.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class DemoController {

    @GetMapping("/hello")
    public String demo() {
        return "Hello, World!";
    }
}
