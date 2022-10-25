package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // (value="패키지" 를 적으면 해당 패키지에서만 오류 잡아 줌)
public class GlobalControllerAdvice {

    //value에 들어가는 예외 목록을 받는다는거 현재같은 경우는 모든 예외상황 다 받아줌.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println("---------------------------");
        //어떤 예외가 발생했는지 보고 그 예외가 발생하면 따로 예외처리 해줄라고 찍어봄
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        System.out.println("---------------------------");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    // 클래스 앞에 적어준 @RestControllerAdvice로 인해서 모든 RestController에서 오류가 잡힘
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("Global하게 오류 잡아주는 곳");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
