package com.example.hello.controller;

import com.example.hello.dto.UserReq;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET) //그냥 RequestMapping만 쓰면 모든 방식으로 접근 가능능
   public String hi(){
        return "hi";
    }

    @GetMapping("/path-variable/{name}") // path-variable 다음에 변수를 담아 올 수 있고, 매개변수와 같은 변수명으로 적어줘야 가능.
    public String pathVariable(@PathVariable String name){
        System.out.println(name);
        return name;
    }


    //key,value값을 받아오기01
    @GetMapping("/query-param")
    public String queryParam(@RequestParam Map<String,String> queryParam){
        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");
            sb.append(entry.getKey()+"="+entry.getValue());
            sb.append("\n");
        });
        return sb.toString();
    }

    //key,value값을 받아오기02
    @GetMapping("/query-param02")
    public String queryParam02(UserReq userReq){
        StringBuilder sb = new StringBuilder();
        System.out.println(userReq.getName());
        System.out.println(userReq.getAge());
        System.out.println(userReq.getEmail());
        return userReq.getName()+" "+userReq.getEmail()+" "+userReq.getAge();
    }
}
