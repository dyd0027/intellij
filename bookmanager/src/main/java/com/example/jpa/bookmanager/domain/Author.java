package com.example.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Author extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

//    @ManyToMany
    @OneToMany
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();
//    public void addBook(Book... book){
//        Collections.addAll(this.books,book);
//    }
    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors){
        Collections.addAll(this.bookAndAuthors,bookAndAuthors);
    }
}
