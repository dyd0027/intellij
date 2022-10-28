# intellij
#### 혼자 SptingBoot를 공부한 내용 정리

## 목차
1. [get](#1_get)
2. [post](#2_post)
3. [put](#3_put)
4. [delete](#4_delete)
5. [response](#5_response)
6. [object-mapper](#6_ocject-mapper)
7. [IOC-DI](#7_IOC-DI)
8. [Sping-ioc](#8_spring-ioc)
---
### Annotation 정리
- Controller
  - @Controller: Spring MVC의 기본적인 컨트롤러로 주로 viw를 반환하기 위해 사용
  - @RestController : Json 형태로 객체 데이터를 반환하기 위해 사용
  - @RequestMapping("/url/url2"): 해당 컨트롤러, 메소드로 오기위해 mapping 시켜주는것. 메소드에 적용 시킬경우 http의 모든 방식으로 접근 가능하다. 특정 방식으로 접근하고 싶은경우 @RequestMapping(path = "/hi", method = RequestMethod.GET) 형식으로 적어주면 됨
-DTO
  - @Data: getter, setter, toString 을 적어줄 필요가 없음.
  - @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class): client 가 요청을 보낼 때 스네이크로 보내는 모든 변수명을 하멜로 바꿔줌.
  - @JsonProperty("car_number"): client 가 요청을 보낼 때 스네이크로 보내는 특정 변수명을 하멜로 바꿔줌.
---
## 1_get
###### hello 프토젝트에서 확인 가능.
- @PathVariable: @GetMapping("/path-variable/{name}") 방식으로 적어주며, 매개변수 부분에 @PathVariable String name 형식으로 매개변수와 같은 변수명으로 적어줘야 함
- key, value 값을 받아오는 방식 (Getcontroller.java 에서 확인 가능)
  - Map 사용: query-param으로 clint가 요청을 보낼때 url 뒤에 ?key=value&key2=value2 식으로 적고 매개변수에 @RequestParam 으로 받아줘야 함.
  - 객체사용(거의 이 방식 사용)
  
---
## 2_post
###### post 프로젝트에서 확인 가능.

- 요청을 받을 때 json object 형식으로 받으며 RequestBody에 담아 보냄 -> 매개변수에 @RequestBody를 적어줘야 함.
- json 형식
  - object는 { "key01":"value01", "key02":"value02"}
  - array는 [{},{}]
  
---
## 3_put
###### put 프로젝트에서 확인 가능.
- array로 받고 싶은경우: Dto에서 List<CarDto> carList;를 선언해 주면 됨.

---
## 4_delete
###### delete 프로젝트에서 확인 가능.
- get방식 사용하는 방식이 같음.

---
## 5_response
###### response 프로젝트에서 확인 가능.
- Controller
  - view를 변환 시켜주며, 반환값은 String으로 하고 return "main.html"로 하면 main.html을 자동으로 찾아감.
- RestController
  - Json을 반환 시켜주며 리턴되는 형식이 어떻게 되든 SpringBoot에서 자동적으로 mapper에서 Json형식으로 response해줌.
  - http 상태가 성공일 경우 200을 반환함.
  - put 방식 같은경우 성공일 경우201(create)로 리턴해 줘야 하는데 이런 경우 아래 이미지와 같이 해주면 됨 ![image](https://user-images.githubusercontent.com/82923905/197134438-fc6a469a-a16a-4830-8230-983406447502.png)

---
## 6_ocject-mapper
###### ocject-mapper 프로젝트에서 확인 가능.
- text -> object // object -> text를 바꿔주는 연습

---
## 7_IOC-DI
###### ioc 프로젝트에서 확인 가능.
- spring 컨테이너 안에 bean들을 저장해두어 spring이 객체를 직접 관리하는 것을 IoC라고 함.
- spring이 제어하는 권한을 가져갔지 때문에 IoC라고 함.
- spring이 주는것을 나는 주입을 받으니깐 DI라고 함

---
## 8_spring-ioc
###### spring-ioc 프로젝트에서 확인 가능.
- @Component
  - Spring에서 자동적으로 bean으로 관리해 달라고 요청하는 어노테이션. 
  - 클래스명대로 bean에 자동적으로 이름이 등록되지만 @Component("다른이름")으로 등록해서 다른이름으로 사용할 수 있음.
- @Qulifier
  - bean에 등록된 이름을 불러오는 어노테이션
  - public Encoder(@Qualifier("base74Encoder") IEncoder iEncoder) 와 같이 
