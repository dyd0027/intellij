package com.example.validation.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

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
}
