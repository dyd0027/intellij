package com.example.exception.advice;

import com.example.exception.controller.ApiController;
import com.example.exception.dto.Error;
import com.example.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestControllerAdvice(basePackageClasses = ApiController.class) // (basePackageClasses =ApiController.class 를 적으면 해당 패키지에서만 오류 잡아 줌)
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
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest){
        List<Error> errorList = new ArrayList<>();

        System.out.println("Global하게 오류 잡아주는 곳");
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error->{
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            String value = fieldError.getRejectedValue().toString();

            Error errorMessage = new Error();
            errorMessage.setMessage(message);
            errorMessage.setField(fieldName);
            errorMessage.setInvalidValue(value);
            errorList.add(errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setMessage("");
        errorResponse.setResultCode("Fail");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity MissingServletRequestParameterException(MissingServletRequestParameterException e,HttpServletRequest httpServletRequest){
        List<Error> errorList = new ArrayList<>();

        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        String invalidValue = e.getMessage();

        Error errorMessage = new Error();
        errorMessage.setMessage(e.getMessage());
        errorMessage.setField(fieldName);
        errorList.add(errorMessage);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setMessage("");
        errorResponse.setResultCode("Fail");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e,HttpServletRequest httpServletRequest){
        List<Error> errorList = new ArrayList<>();

        e.getConstraintViolations().forEach(error->{
            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(),false);
            List<Path.Node> list = stream.collect(Collectors.toList());

            String field = list.get(list.size()-1).getName();
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            Error errorMessage = new Error();
            errorMessage.setMessage(message);
            errorMessage.setField(field);
            errorMessage.setInvalidValue(invalidValue);
            errorList.add(errorMessage);

        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setMessage("");
        errorResponse.setResultCode("Fail");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
