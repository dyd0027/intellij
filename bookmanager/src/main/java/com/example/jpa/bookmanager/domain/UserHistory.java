package com.example.jpa.bookmanager.domain;

import com.example.jpa.bookmanager.domain.listener.Auditable;
import com.example.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = UserEntityListener.class)
public class UserHistory extends BaseEntity {
    @Id // pk임
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동적으로 순차적으로 값이 증가 함
    private Long id;
//    @Column(name = "user_id",insertable = false,updatable = false)
//    private Long userId;

    private String name;

    private String email;

    @ManyToOne
    private User user;
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
}
