package com.example.put.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) //client 가 요청을 보낼 때 스네이크로 보내는걸 하멜로 바꿔줌.
public class ReqDto {
    private String name;
    private int age;

    private List<CarDto> carList;
}
