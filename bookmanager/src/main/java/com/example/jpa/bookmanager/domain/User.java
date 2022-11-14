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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

}
