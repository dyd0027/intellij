package com.example.server.controller;

import com.example.server.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/server")
public class ServerApiController {
    @GetMapping("")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return user;
    }
}
