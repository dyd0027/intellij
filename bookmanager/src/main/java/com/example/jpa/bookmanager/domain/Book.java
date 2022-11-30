package com.example.jpa.bookmanager.domain;

import com.example.jpa.bookmanager.domain.listener.Auditable;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = AuditingEntityListener.class)
@Where(clause = "deleted = false") //모든 쿼리 실행시 where절에 저게 붙음.
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long authorId;

//    private Long publisherId;

    private String category;

    @OneToOne(mappedBy = "book") // 이러면 해당 필드가 생성되지 않지만 확인은 할 수 있음.
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private Publisher publisher;

    private boolean deleted;
//    @ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();
    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors){
        Collections.addAll(this.bookAndAuthors,bookAndAuthors);
    }
//    public void addAuthor(Author... author){
//        Collections.addAll(this.authors,author);
//
//    }
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    @PrePersist
//    public void prePersist(){
//        this.createdAt=LocalDateTime.now();
//        this.updatedAt=LocalDateTime.now();
//    }
//    @PreUpdate
//    public void preUpdate(){
//        this.updatedAt=LocalDateTime.now();
//    }


}
