package com.br.library.library.domain;

import com.br.library.library.dtos.BookPostBody;
import com.br.library.library.dtos.BookPutBody;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Setter
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private String author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate datePublished;

    private boolean available;

    public Book(BookPostBody bookPostBody) {
        this.title = bookPostBody.getTitle();
        this.genre = bookPostBody.getGenre();
        this.author = bookPostBody.getAuthor();
        this.datePublished = bookPostBody.getDatePublished();
    }
    public Book(BookPutBody bookPutBody) {
        this.id = bookPutBody.getId();
        this.title = bookPutBody.getTitle();
        this.genre = bookPutBody.getGenre();
        this.author = bookPutBody.getAuthor();
        this.datePublished = bookPutBody.getDatePublished();
        this.available = bookPutBody.isAvailable();

    }

}
