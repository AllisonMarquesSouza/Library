package com.br.library.library.repository;

import com.br.library.library.domain.Book;
import com.br.library.library.enums.StatusToReserve;
import com.br.library.library.util.BookCreate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@Log4j2
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;


    @Test
    @DisplayName("Get Book Available from database when is successful")
    void findAllBooksAvailableSuccess(){

        Book bookAvailable = BookCreate.createBookAvailable();
        Book bookReserved = BookCreate.createBookReserved();

        bookRepository.save(bookAvailable);
        bookRepository.save(bookReserved);

        List<Book> result = bookRepository.findAllBooksAvailable();
        assertEquals(result.size(), 1);
        assertEquals(result.get(0), bookAvailable);
        assertEquals(result.get(0).getStatusToReserve(), StatusToReserve.AVAILABLE);
    }



}