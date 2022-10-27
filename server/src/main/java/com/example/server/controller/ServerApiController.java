package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/naver")
    public String naver(){

        String query ="갈비집";
        String code = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));


        //https://openapi.naver.com/v1/search/local.json
        // ?query=%EC%A3%BC%EC%8B%9D
        // &display=10
        // &start=1
        // &sort=random
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","곱창집")
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort","random")
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","pGwnhtaLpo6zA6UXR1x5")
                .header("X-Naver-Client-Secret","oYpHxLBjnp")
                .build();
        ResponseEntity<String> result = restTemplate.exchange(req,String.class);
        return result.getBody();
    }

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
