package com.example.object_mapper;

import com.example.object_mapper.dto.Car;
import com.example.object_mapper.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("스따또");

        ObjectMapper objectMapper = new ObjectMapper();
        User user= new User();
        user.setAge(27);
        user.setName("권용휘");

        Car car = new Car();
        car.setCarNumber("11가11111");
        car.setName("K5");
        car.setType("세단");
        Car car2 = new Car();
        car2.setCarNumber("11가3333");
        car2.setName("Q5");
        car2.setType("SUV");
        List<Car> carList = Arrays.asList(car,car2);

        user.setCarList(carList);
        System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        //jsonarray부분 파싱
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name: "+_name+"/age: "+_age);
        ArrayNode arrayNode = (ArrayNode) jsonNode.get("car_list");
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);

        //json value 값 바꾸기
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name","이름 바뀌어랏");
        objectNode.put("age",100);
        System.out.println(objectNode.toPrettyString());
    }
}
