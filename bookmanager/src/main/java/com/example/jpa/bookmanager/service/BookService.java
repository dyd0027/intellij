package com.example.jpa.bookmanager.service;

import com.example.jpa.bookmanager.domain.Author;
import com.example.jpa.bookmanager.domain.Book;
import com.example.jpa.bookmanager.repository.AuthorRepository;
import com.example.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor // Autowired없이 생성자 만들기
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;
    public void put(){
        this.putBookAndAuthor();
    }

    @Transactional(rollbackFor = Exception.class) // @Transaction 같은경우 transactionAspectSupport.java 의 670라인의 코드를 확인하면 RunTimeException만 예외처리 되게 되어있음.하지만 이렇게 적어주면 해당 클래스도 예외 처리 됨.
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("용휘짱");
        bookRepository.save(book);

        Author author = new Author();
        author.setName("용휘");
        authorRepository.save(author);
        throw new RuntimeException("오류가 나서 DB commit밯생하지 않습니다라쥐");
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get(Long id){
        System.out.println("1번>>>>>>> :" +bookRepository.findById(id));
        System.out.println("2번>>>>>>> :" +bookRepository.findAll());

        entityManager.clear();

        System.out.println("3번>>>>>>> :" +bookRepository.findById(id));
        System.out.println("4번>>>>>>> :" +bookRepository.findAll());
        bookRepository.update();
        entityManager.clear();
//        Book book = bookRepository.findById(id).get();
//        book.setName("바뀔까?");
//        bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.REQUIRED) // @Transaction 같은경우 transactionAspectSupport.java 의 670라인의 코드를 확인하면 RunTimeException만 예외처리 되게 되어있음.하지만 이렇게 적어주면 해당 클래스도 예외 처리 됨.
    public void putBookAndAuthorPropagation() {
        Book book = new Book();
        book.setName("용휘짱");
        bookRepository.save(book);

        try{
            authorService.putAuthor();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
       throw new RuntimeException("오오 과연?!");
    }
}