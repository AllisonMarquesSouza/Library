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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test") //identificando o properties do test
@Log4j2
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;


    @Test
    @DisplayName("Get Book Available from database when is successful")
    void findAllStatusToReserveIsAVAILABLESuccess(){

        Book bookAvailable = BookCreate.createBook();
        bookAvailable.setStatusToReserve(StatusToReserve.AVAILABLE);
        bookRepository.save(bookAvailable);


        Book bookCanceled = BookCreate.createBook2();
        bookCanceled.setStatusToReserve(StatusToReserve.CANCELED);
        bookRepository.save(bookCanceled);

        List<Book> result = bookRepository.findAllStatusToReserveIsAVAILABLE();
        log.info(result.toString());
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(bookAvailable);
        assertThat(result.get(0).getStatusToReserve()).isEqualTo(StatusToReserve.AVAILABLE);

    }



}