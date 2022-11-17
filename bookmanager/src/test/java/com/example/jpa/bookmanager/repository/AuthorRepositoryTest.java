package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Author;
import com.example.jpa.bookmanager.domain.Book;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Test
    @Transactional
    void manyToMany(){
        Book book1 = givenBook("책1");
        Book book2 = givenBook("책22");
        Book book3 = givenBook("책333");
        Book book4 = givenBook("책4444");

        Author author1 = givenAuthor("yh");
        Author author2 = givenAuthor("Gwon");


//        book1.setAuthors(Lists.newArrayList(author1));
//        book2.setAuthors(Lists.newArrayList(author2));
//        book3.setAuthors(Lists.newArrayList(author1,author2));
//        book4.setAuthors(Lists.newArrayList(author1,author2));
//        author1.setBooks(Lists.newArrayList(book1,book3,book4));
//        author2.setBooks(Lists.newArrayList(book2,book3,book4));
        // 원래는 위에처럼 하나 코드가 길어지기 때문에 아래와 같이 메소드 만들어서 함
        book1.addAuthor(author1);
        book2.addAuthor((author2));
        book3.addAuthor(author1,author2);
        book4.addAuthor(author1,author2);
        author1.addBook(book1,book3,book4);
        author2.addBook(book2,book3,book4);

        bookRepository.saveAll(Lists.newArrayList(book1,book2,book3,book4));
        authorRepository.saveAll(Lists.newArrayList(author1,author2));
        System.out.println("authors through book: "+bookRepository.findAll().get(2).getAuthors());
        System.out.println("books through author: "+authorRepository.findAll().get(0).getBooks());
    }
    private Book givenBook(String name){
        Book book = new Book();
        book.setName(name);
        return bookRepository.save(book);
    }
    private Author givenAuthor(String name){
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }
}