package com.example.jpa.bookmanager.service;

import com.example.jpa.bookmanager.domain.Book;
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
        try{
            bookService.putBookAndAuthor();
            //아래의 함수는 transaction이 되질 않음.
//            bookService.put();
        }catch(Exception e){
            System.out.println(">>>>>>>"+e.getMessage());
        }

        System.out.println("books: "+bookRepository.findAll());
        System.out.println("author: "+authorRepository.findAll());
    }

    @Test
    void isolationTest(){
        Book book = new Book();
        book.setName("용ㅇ휘찡찡");
        bookRepository.save(book);

        bookService.get(1L);

        System.out.println("Test>>>>>"+bookRepository.findAll());
    }
}