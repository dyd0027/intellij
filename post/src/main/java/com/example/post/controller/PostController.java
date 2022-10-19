package com.example.post.controller;

import com.example.post.dto.PostReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {
    @PostMapping("/post")
    public void post(@RequestBody  Map<String, Object> requestMap){
        requestMap.entrySet().forEach(stringObjectEntry -> {
            System.out.println("key : "+stringObjectEntry.getKey());
            System.out.println("value : "+stringObjectEntry.getValue());
        });
    }
    @PostMapping("/post2")
    public void post2(@RequestBody PostReq postReq){
        System.out.println(postReq.toString());

    }
}
