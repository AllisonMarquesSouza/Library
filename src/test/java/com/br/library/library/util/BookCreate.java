package com.br.library.library.util;

import com.br.library.library.domain.Book;
import com.br.library.library.dtos.book.BookDtoPut;
import com.br.library.library.enums.StatusToReserve;

import java.time.LocalDate;

public class BookCreate {

    public static Book createBook() {
        return Book.builder()
                .title("Shadow Slave").
                genre("Romance")
                .author("Guilt333")
                .datePublished(LocalDate.now())
                .statusToReserve(StatusToReserve.RESERVED)
                .build();

    }

    public static Book createBook2() {
        return Book.builder()
                .title("Shadow Slave 2 ").
                genre("Romance 2 ")
                .author("Guilt333 2 ")
                .datePublished(LocalDate.now().plusDays(5))
                .statusToReserve(StatusToReserve.RESERVED)
                .build();

    }


    public static Book createBookValid() {
        return Book.builder()
                .id(1L)
                .title("Shadow Slave").
                genre("Romance")
                .author("Guilt333")
                .datePublished(LocalDate.now())
                .statusToReserve(StatusToReserve.AVAILABLE)
                .build();

    }
    public static BookDtoPut createBookDtoPut() {
        return BookDtoPut.builder()
                .id(1L)
                .title("Shadow Slave").
                genre("Romance")
                .author("Guilt333")
                .datePublished(LocalDate.now())
                .statusToReserve(StatusToReserve.AVAILABLE)
                .build();

    }
}
