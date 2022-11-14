package com.example.jpa.bookmanager.domain;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@RequiredArgsConstructor // @NonNull이라고 적힌 변수들로만 이루어진 생성자 만들 수 있음.
@Builder
@Entity  // Id가 꼭 필요함
@Table(name="users",indexes = {@Index(columnList = "name")},uniqueConstraints = {@UniqueConstraint(columnNames = "email")}) // table이름 바꿈,name으로 index만듦,email에 유니크값 넣어줌
public class User {
    @Id // pk임
    @GeneratedValue // 자동적으로 순차적으로 값이 증가 함
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;
//    @Column(name ="credat",unique = true,nullable = false) 이런식으로 컬럼 속성 수정 가능. 더 많은 내용이 있으니 인터페이스 확인.
//    @Column(updatable = false)  update시 이 친구는 반영 안됨
    private LocalDateTime createdAt;
//    @Column(insertable = false) insert시 이 친구는 반영 안됨
    private LocalDateTime updatedAt;
//    @Transient DB에는 실반영이 안되게 해줌.
//    private String testData;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;
    @Enumerated(value = EnumType.STRING) // String으로 안하면 Gender에 명시해 준 순서대로 0부터 나와서 저장이 됨.->이건 잠재적버그일으킴
    private Gender gender; 
}
