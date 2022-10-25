package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class User {
    @NotBlank
    private String name;
    @Max(value = 90,message = "나이는 90이하 입니다람쥐")
    private int age;
    @Email // email형식에 맞지 않으면
    private String email;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = "핸드폰 번호의 양식과 맞지 않습니다. xxx-xxxx-xxxx") //해당 정규식에 맞지 않으면
    private String phoneNumber;
//    @Size(min = 6, max = 6) //yyyyMM
    @YearMonth()
    private String reqYearMonth;

    //@valid 꼭 써줘야 그 안에 있는 클래스의 어노테이션들이 먹음!!!!!!!
    @Valid
    private List<Car> cars;


    //boolean 리턴하는 함수는 앞에 is를 붙여야 함.
    //AssertTrue를 통과해야지 성공
    //annotation을 만들면 AssertTrue 안써도 됨.
//    @AssertTrue(message = "yyyyMM형식에 맞지 않습니다.")
//    public boolean isReqYearMonthValidation(){
//        System.out.println("assert true call");
//        try {
//            LocalDate localDate = LocalDate.parse(getReqYearMonth()+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
//        }catch (Exception e){
//            return false;
//        }
//
//        return true;
//    }
}
