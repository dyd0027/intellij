# intellij
#### 혼자 SpringBoot를 공부한 내용 정리

## 목차
1. [get](#1_get)
2. [post](#2_post)
3. [put](#3_put)
4. [delete](#4_delete)
5. [response](#5_response)
6. [object-mapper](#6_ocject-mapper)
7. [IOC-DI](#7_IOC-DI)
8. [Sping-ioc](#8_spring-ioc)
9. [aop](#9_aop)
10. [validation](#10_validation)
11. [exception](#11_exception)
12. [filter](#12_filter)
13. [interceptor](#13_interceptor)
14. [client(generic으로 변수 주고 받기)](#14_client)
15. [JUnit](#15_JUnit)
16. [swagger](#16_swagger)
17. [restaurant](#17_restaurant)
18. [JPA](#18_JPA)
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
  - public Encoder(@Qualifier("base74Encoder") IEncoder iEncoder) 와 같이 사용.
- @Configuration
  - bean을 여러개 등록하고 싶을 때 사용
  - ![image](https://user-images.githubusercontent.com/82923905/198556761-0505db5c-f516-4b23-971a-fb20126ee449.png)

---
## 9_aop
###### aop 프로젝트에서 확인 가능.
- 각 메소드마다 똑같이 들어가는 코드를 작성하면 코드 지저분하니 aop 사용하기 (코드 재활용)
- 메소드 시작과 끝에 변수, 리턴값들이 잘 작동되나 확인같은게 하고 싶을 때
- gradle 에 implementation 'org.springframework.boot:spring-boot-starter-aop' 삽입
- 해당 클래스에 @Aspect, @Component 작성해 줘야 함.
- 자세한 사용법 및 어노테이션 활용은 com.example.aop.aop.Parameter.java 확인
- annotation을 직접 만들어서 사용 -> com.example.aop.aop.DecodeAop.java + com.example.aop.annotatioin.Decode확인 

---
## 10_validation
###### validation 프로젝트에서 확인 가능.
- client가 변수를 보낼 때 정규식에 맞지않거나 or null값 등을 보낼 때 바로 exception해주는 annotion
- 자세한 사용법 및 어노테이션 활용은 com.example.validation.dto.Car or User 확인
- @Max, @Email, @Min, @NotBlank 같은 어노테이션이 있지만 내가 마음에 드는 어노테이션이 없는경우 직접 만들어서 사용 가능
- annotation을 직접 만들어서 사용 -> com.example.validation.validator + com.example.aop.annotatioin확인 


---
## 11_exception
###### exception 프로젝트에서 확인 가능.
- 특정 오류의 class들만 잡아 줄수도 있음.
- 오류가나는 메소드가 있는 컨트롤러에서 오류를 잡을 경우 광역으로 잡는거보다 우선순위.
- 하나의 틀만 만들어 놓으면 광대하게 사용할 수 있는 장점이 있음.

---
## 12_filter
###### filter 프로젝트에서 확인 가능.
- web application에서 관리되는 영역으로 요청/응답에 대하여 최초/최동 단계에 위치.
- filter -> interceptor -> aop 순으로 요청 감.
- 전처리가 아닌 후처리에 (doFilter기준) 데이터를 뽑을 수 있음.

---
## 13_interceptor
###### interceptor 프로젝트에서 확인 가능.
- filter와 매우 유사하지만 차이점은 sping context에 등록이 된다.
- 주로 인증 단계를 처리할 때 사용.
 
---
## 14_client
###### client 프로젝트에서 확인 가능.
- client와 server에서 json 형태를 어떻게 주고 받을지 충분한 회의를 갖춘 후 그거에 맞게 rest-templete 작성하기 예시.
- 변수를 generic으로 주고 받기.
  
---
## 15_JUnit
###### calculator, spring-calculator 프로젝트에서 확인 가능.
- JUnit: Java기반의 단위테스트를 위한 프레임워크.
- annotation 기반으로 테스트를 지원하며 Assert를 통하여 예상, 실제를 통해 검증.
- Mock: 실제 api 호출하면 값이 바뀔 수 있으니 init할 때 내가 return값을 임의로 지정할 수 있음.

---
## 16_swagger
###### swagger 프로젝트에서 확인 가능.
- swagger: 개발한 rest api를 편리하게 문서화 해주고, 이를 통해서 관리 밑 제3의 사용자가 편리하게 api를 호출해보고 테스트 할 수 있는 프로젝트.
- spring boot에서는 간단하게 gradle에 의존성 주입만 시켜주면 사용할 수 있다.
  
---
## 17_restaurant
#### 네이버 지도 OPEN API를 사용하여 간단하게 맛집을 검색해볼 수 있다. 해당 맛집을 위시리스트에 저장, 삭제하고 방문횟수를 카운트 하기.
- 개발 환경
  - Java11
  - IntelliJ
  - Spring Boot
  - OPEN API
- 실행 결과
  - 초기화면
  ![image](https://user-images.githubusercontent.com/82923905/200270037-e3d39ea5-1af3-4b4e-8a34-6a63bd3ca931.png)
  - 검색
  ![image](https://user-images.githubusercontent.com/82923905/200270694-d85bb2ce-e7b9-4c32-8528-c41faf90021f.png)
  - 위시리스트 추가
  ![image](https://user-images.githubusercontent.com/82923905/200270782-e84a8a06-dc1a-4d78-8990-ef86442c17bc.png)
  - 방문횟수 추가
  ![image](https://user-images.githubusercontent.com/82923905/200270973-44fa125c-243c-4d05-9afd-8a9d548e9039.png)
  - 위시리스트 삭제
---
## 18_JPA
##### JPA에 대하여 공부 ->오픈소스를 보는 습관 가지면 좋음. 특히 jpa 라이브러리는 가독성이 좋게 만들어진 코드들이다
##### bookmanager 프로젝트에서 확인 가능
- ORM: 자바객체(entity)와 데이터베이스 사이 관계를 연결해주는거 (만약 없다면 select로 데이터들을 뽑아와서 하나하나 매핑해야함)
- JPA(Java Persistence API): ORM의 좀더 구체적으로 기능을 정의한 스펙
- Hibernate: JPA 인터페이스를 구현한 것
- data.sql: Hibernate 초기화를 통해 생성된 스키마에다가 데이터를 채우기를 위해서 data.sql가 실행되기를 원한다면 application.yml(또는 properties)에 spring.jpa.defer-datasource-initialization 옵션 값을 true로 추가해주어야 한다.
- 쿼리메소드 findBy~ 등등 이러거 전부 자동으로 제공 됨 (단, 네이밍규칙 어긋나면 런타임오류 남)
- @Entity : 해당 객체는 Spring에서 객체로 저장됨 -> PK가 필요함 따라서, @Id 적어줘야 함
- listener: @PrePersist, @PreUpdate를 자주 사용 -> 꼭 넣어줘야 하는 값 들을 entity에 정의 함으로서 실수를 줄임 ->이것또한 광역으로 설정 가능. MyEntityListener확인
- @OneToMany: 1대N인 경우 1인 엔티티에 적어 줌 -> One쪽에다가 @JoinColoum(name ="One_id")적어줌으로 중간에 생성되는 테이블 없어짐
- @ManyToOne: N대1인 경우 N인 엔티티에 적어 줌
- @ManyToMany: N대M인 경우 양 쪽에 다 적어줌 -> @JoinColumn을 사용하지 않아야 중간다리의 테이블 생성 되므로 적으면 안됨.
- 영속성 컨텍스트(persistence context): 엔티티를 영구 저장하는 환경 // 영속성: 지워지지 않고 영구적이다. 컨텍스트: 관리해줌
  - EntityManager(인터페이스)를 통해 엔티티를 저장하거나 조회하면 EntityManager는 영속성 컨텍스트에 엔티티를 보관하고 관리한다.
- EntityManager는 Cache를 가지고 있음->실제로 save메소드를 실행하는 시점에서 디비에 저장이 되지 않음->영속성컨텍스트와 실제 디비에 데이터gap이 발생.
- JPA의 1차 캐시@Trancational 을 붙힌다면 실제 DB에서 조회하지 않고 EntityCache에서 직접 처리함
  - 1차 캐시를 사용함으로서 성능저하를 막을 수 있음.(단, id로 조회 할 때만 캐시남음)
- @Trancational을 사용하면 Test메소드 하나에 transactioa이 묶이게 됨
  - 하지만 userRepository.flush()를 사용하면 바로바로 디비에 적용됨.
  - flush를 하지 않으면 rollback이 발생되어 저장되지 않음.
  - 영속성 컨텍스트가 flush 되는 시점
    - flush라고 직접 명시
    - Trancation이 끝나서 해당 쿼리가 커밋되는 시점.
    - 복잡한 쿼리가 실행 될 때
      - ex) user의 정보가 5개라고 가정 -> 1번의 유저만 save를 함 -> 모든 유저의 정보를 가져온다고 가정
			->그럼 1번의 유저는 최신정보(즉, 영속성 컨텍스트안에 있음) 나머지는 예전정보(즉, DB상에 있음)
			->이럴경우 flush를 해서 1번 유저를 실제DB에 적용시켜 merge를 시킴.
- @Trancational안에 있는 메소드는 그 안에 있는 모든 crud가 하나의 trancation안에서 움직임
  - Exception같은경우 트랜잭션과 무관함 그래서 예외처리할 때 exception은 개발한 개발자 책임임...그래서runtime같은 예외처리로 걸러줘야 함.
    - transcationAspectSupport.java 670의 코드를 보면 확인 가능.
- bean에 등록된 메소드에서 다른 bean에 등록된 @Trancational이 붙은 함수를 호출 시 annotation이 먹지 않음.
- Isolation(격리성): 경리성은 동시에 실행되는 트랜잭션이 서로에게 영향을 미치지 않도록 격리힌다.
	- READ UNCOMMITTED: 커밋되지 않는 읽기 -> 데이터 정확성에 문제가 있음.
	- READ COMMITTEDl: 커밋된 읽기
	- REPEATABLE READ: 반복 가능한 읽기
	- SERIALRZABLE: 직렬화 기능 -> 데이터 정확성은 좋으나 웨이팅이 많이 발생해서 성능에 문제가 있음.
- Propagation: 트랜잭션의 영역, 바운더리를 지정하기 위한 설정.
	- ![image](https://user-images.githubusercontent.com/82923905/205480070-0b00deb8-ef1c-4cc1-9374-6526b4cc0d4a.png)
- Cascade:  영속성 전이 ->default는 빈 값이라서 값을 채워 넣어야 함.
	- @OneToMany, @OneToOne, @ManyToOne에서 사용 가능
	- CascadeType.PERSIST: insert할 때 해당 컬럼도 insert 되게끔.
	- CascadeType.MERGE: update할 때
- @Where(clause = "deleted = false"): 모든 쿼리 실행 시 where 절에 저게 들어감.
- @Query: JPQL을 사용하며 실제 DB에서 사용하는 컬럼명이 아닌 자바에 직접 만든 엔티티에서 정의한 이름을 사용함.
	- nativeQuery를 사용하면 JPA쿼리에서 사용되는 엔티티 사용 불가
		- 따라서 @Trancational도 적어줘야 함.
		- @Nodifying을 사용해야 update쿼리가 실행 됨.
- converter
	- converter하는 법 : 클래스 생성 -> 클래스컨버스 생성 후 implements ArributeConverter<클래스,원하는 자료형> -> 엔티티에 원하는 클래스 선언 후 @Convert, -> Converter가서 @Converter 적어 줌.
		- @Converter: converter로 등록   ->  autoApply = true : 해당 객체를 불러올 때 자동적으로 매칭 됨. 그게 아니라면 밑에 @Convert 사용
		- @Convert: converter를 가지고 올 수 있음 @Convert(converter=xxxx.class)
- embeded
	- embeded하는 법: 클래스 생성 -> 클래스에 @Embededable 작성 -> 엔티티에서 클래스 선언 후 @Embeded 작성
		- 이러면 해당 엔티티 테이블에 embeded클래스에 있는 변수명대로 컬럼이 생김
	- Addess같은 경우 집주소, 회사주소가 있으므로 @AttributeOverrides사용 (User.java 확인)
- N+1문제: 쿼리가 많이 실행 됨
	- fetch : eager,lazy 타입이 있음 -> eager는 모든 쿼리 실행, lazy는 필요할 때만 퀴리 실행 
	- @ManyToOne, @OnetoOne : defaultFetch은 eager 타입, 나머지는 lazy
	- 해결방법1: 쿼리를 커스텀 해서 사용. ->findAll()같은 함수를 사용하는게 아니라.
	- 해결방법2: EntityGraph 사용. -> 작성 된 커리문 위에 @EntityGraph(attributePaths="원하는 컬럼")하면 됨.
- JPA는 insert를 하거나 update를 할 때 모든 컬럼값을 바꿔주지만 @DynamicInsert,@DynamicUpdate를 통해 변경된 값만 넣어 줄 수 있음.
