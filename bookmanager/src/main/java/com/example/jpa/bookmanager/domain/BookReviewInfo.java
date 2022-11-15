package com.example.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long bookId;
    @OneToOne(optional = false) //optional = false : not null 1대1 관계 -> 테이블 생성될 때 Book에 있는 pk만 생성됨....갓갓
    private Book book;

    private float averageReviewScore;

    private int reviewCount;
}
