package com.example.filter.controller;

import com.example.filter.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class ApiController {
    @PostMapping("")
    public User user(@RequestBody User user){
        //로그에 매개변수 여러개 넣을때는 밑에처럼
        //log.info("User : {},{}",user,user);

        log.info("User : {}",user);
        return user;
    }
}
