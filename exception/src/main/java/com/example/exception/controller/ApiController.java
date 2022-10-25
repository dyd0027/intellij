package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {
    @GetMapping("") //required = false은 필수는 아니다. 없으면 null로 대채
    public User get( @RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        User user = new User();
        user.setAge(age);
        user.setName(name);

        int a = 10+age;

        return user;
    }
    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }

    //예외 잡는 우선순위는 해당 컨트롤러에 적어주는 것 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("ApiController안에 있는 오류 잡아주는 곳");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
