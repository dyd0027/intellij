package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//html을 리턴해 줄때는 그냥 컨트롤러
@Controller
public class PageController {

    @RequestMapping("/main")
    public String main(){
        //String 으로 반환하면 자동으로 main.html을 찾아감요
        return "main.html";
    }

    //원래 controller에서 이런건 안하는데 혹시 필요하면 이런 식으로하면 됨.-> 원래는 RestController에서 하는게 맞음.
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();
        user.setAddress("연신내");
        user.setAge(27);
        user.setName("jjj");
        return user;
    }
}
