package com.example.put.controller;

import com.example.put.dto.ReqDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutController {

    @PutMapping("/put/{userId}") //만약 pathVariable을 사용 할 경우 뒤에 url에 값을 넣어줘야 작동함.
    public ReqDto put(@RequestBody ReqDto reqDto, @PathVariable Long userId){
        System.out.println(reqDto.toString());
        System.out.println("userId : " + userId);
        return reqDto;
    }
}
