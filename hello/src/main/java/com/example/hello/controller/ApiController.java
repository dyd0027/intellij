package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 해당 class는 rest api처리하는 controller
@RequestMapping("api") // requestMapping URI를 지정해주는 Annotation
public class ApiController {
    @GetMapping("/hello") // http://localhost:9090/api/hello
    public String hello(){
        return "hello Spring boot";
    }
}
