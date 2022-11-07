package com.example.jpa.bookmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String hellWorld(){
        return "hello-world";
    }
}
