package com.example.springioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

//@Component 는 Spring에서 자동적으로 bean으로 관리해달라는 어노테이션
//다른 곳에서 사용할 때 이름을 갖다 붙혀야 하는데 원래는 base64Encoder 로도 되나 밑에처럼 이름을 붙혀서 사용할 수 있음.
@Component("base74Encoder")
public class Base64Encoder implements IEncoder {
    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
