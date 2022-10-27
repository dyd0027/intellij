package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    @Autowired //생성자 만들어주기
    private RestTemplateService restTemplateService;

    @GetMapping("")
    public UserResponse getHello(){
        return restTemplateService.hello();
    }
    @GetMapping("/post")
    public UserResponse postHello(){
        return restTemplateService.post();
    }
    @GetMapping("/exchange")
    public UserResponse exchangeHello(){
        return restTemplateService.exchange();
    }

    @GetMapping("/generic")
    public Req<UserResponse> genericHello(){
        return restTemplateService.genericExchange();
    }
}
