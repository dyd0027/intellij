package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
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

    @PostMapping("/user/{userId}/name/{userName}")
    public User post(@RequestBody User user,@PathVariable int userId ,@PathVariable String userName ){
        log.info("userId : {}, userName : {}",userId, userName);
        log.info("client req: {}",user);
        return user;
    }

    @PostMapping("/exchange/user/{userId}/name/{userName}")
    public User exchange(@RequestBody User user,
                         @PathVariable int userId ,
                         @PathVariable String userName,
                         @RequestHeader("x-authorization") String authorization,
                         @RequestHeader("custom-header") String customHeader){
        log.info("userId : {}, userName : {}",userId, userName);
        log.info("authorization : {}, customHeader : {}",authorization, customHeader);
        log.info("client req: {}",user);
        return user;
    }

    @PostMapping("/generic/user/{userId}/name/{userName}")
    public Req<User> generic(@RequestBody Req<User> user,
                         @PathVariable int userId ,
                         @PathVariable String userName,
                         @RequestHeader("x-authorization") String authorization,
                         @RequestHeader("custom-header") String customHeader){
        log.info("userId : {}, userName : {}",userId, userName);
        log.info("authorization : {}, customHeader : {}",authorization, customHeader);
        log.info("client req: {}",user);
        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setRBody(user.getRBody());
        return response;
    }
}
