package com.example.object_mapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private String name;
    private int age;
    @JsonProperty("car_list")
    private List<Car> carList;
}
