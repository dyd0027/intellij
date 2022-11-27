package com.example.jpa.bookmanager.service;

import com.example.jpa.bookmanager.repository.AuthorRepository;
import com.example.jpa.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookService bookService;

    @Test
    void transactionTest(){
        bookService.putBookAndAuthor();
        System.out.println("books: "+bookRepository.findAll());
        System.out.println("author: "+authorRepository.findAll());
    }
}