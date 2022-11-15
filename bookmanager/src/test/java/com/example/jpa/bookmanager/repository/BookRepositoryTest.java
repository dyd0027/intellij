package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("용휘짱");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());

    }
}
