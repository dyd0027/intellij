package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    //Content-Type: text
    @GetMapping("/text")
    public String test(@RequestParam String account){
        return account;
    }

    //Content-Type: Json
    //req -> object mapper-> object -> method -> object -> object mapper-> json -> response
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;
    }

    //put 같은경우 201(생성됐다)로 코드 내려줘야 함. 그래서 ResponseEntity 사용해서 수정 가능.
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
