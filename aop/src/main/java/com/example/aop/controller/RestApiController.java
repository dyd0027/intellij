package com.example.aop.controller;

import com.example.aop.annotation.Decode;
import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        System.out.println("get method 안이 실행 중");

        return id+" "+name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
        System.out.println("post method 안이 실행 중");
        return user;
    }


    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        //db 로직이 2초 정도 걸린다고 가정
        Thread.sleep(1000*2);
    }


    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("put method 안이 실행 중");
        return user;
    }
}
