package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    // validation을 사용 할 거면 매개변수 앞에 valid라고 붙혀 줘야 함
    // @Valid 에서 나오는 에러 내용을 BindingResult에서 확인할 수 있음.
    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult){
        System.out.println(user);

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError ->{
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();
                System.out.println("field: "+ field.getField());
                System.out.println("message: "+message);
                sb.append("field: "+ field.getField()+"\n");
                sb.append("message: "+message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        return ResponseEntity.ok(user);
    }
}
