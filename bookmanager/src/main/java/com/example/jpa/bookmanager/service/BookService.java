package com.example.jpa.bookmanager.service;

import com.example.jpa.bookmanager.domain.Author;
import com.example.jpa.bookmanager.domain.Book;
import com.example.jpa.bookmanager.repository.AuthorRepository;
import com.example.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // Autowired없이 생성자 만들기
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    @Transactional
    public void putBookAndAuthor(){
        Book book = new Book();
        book.setName("용휘짱");
        bookRepository.save(book);

        Author author = new Author();
        author.setName("용휘");
        authorRepository.save(author);
        throw new RuntimeException("오류가 나서 DB commit밯생하지 않습니다라쥐");
    }

}