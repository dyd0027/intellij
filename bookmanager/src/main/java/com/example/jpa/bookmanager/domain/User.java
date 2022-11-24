package com.example.jpa.bookmanager.domain;

import com.example.jpa.bookmanager.domain.listener.Auditable;
import com.example.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor // @NonNull이라고 적힌 변수들로만 이루어진 생성자 만들 수 있음.
@Builder
@Entity  // Id가 꼭 필요함
@Table(name="users"/*,indexes = {@Index(columnList = "name")},uniqueConstraints = {@UniqueConstraint(columnNames = "email")}*/) // table이름 바꿈,name으로 index만듦,email에 유니크값 넣어줌
@EntityListeners(value = {UserEntityListener.class})
public class User extends BaseEntity {
    @Id // pk임
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동적으로 순차적으로 값이 증가 함
    private Long id;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",insertable = false,updatable = false) // userHistory테이블에서 userId로 FK생성됨,user테이블은 insert와 update 할 때 userHistory에 간섭x
    @ToString.Exclude
    @Builder.Default
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL) //cascade 사용
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @NonNull
    private String name;

    @NonNull
    private String email;
//    @Column(name ="credat",unique = true,nullable = false) 이런식으로 컬럼 속성 수정 가능. 더 많은 내용이 있으니 인터페이스 확인.
//    @Column(updatable = false)  update시 이 친구는 반영 안됨
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @Column(insertable = false) insert시 이 친구는 반영 안됨
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
//    @Transient DB에는 실반영이 안되게 해줌.
//    private String testData;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;
    @Enumerated(value = EnumType.STRING) // String으로 안하면 Gender에 명시해 준 순서대로 0부터 나와서 저장이 됨.->이건 잠재적버그일으킴
    private Gender gender;

//    @PrePersist
//    public void prePersist(){
//        this.setCreatedAt(LocalDateTime.now());
//        this.setUpdatedAt(LocalDateTime.now());
//    }
//    @PostPersist
//    public void postPersist(){
//        System.out.println(">>>>>>>postPersist");
//    }
//    @PreUpdate
//    public void preUpdate(){
//        this.setUpdatedAt(LocalDateTime.now());
//    }
//    @PostUpdate
//    public void postUpdate(){
//        System.out.println(">>>>>>>postUpdate");
//    }
//    @PreRemove
//    public void preRemove(){
//        System.out.println(">>>>>>>preRemove");
//    }
//    @PostRemove
//    public void postRemove(){
//        System.out.println(">>>>>>>postRemove");
//    }
//    @PostLoad
//    public void postLoad(){
//        System.out.println(">>>>>>>postLoad");
//    }
}
