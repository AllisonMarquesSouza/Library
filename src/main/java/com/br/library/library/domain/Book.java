package com.br.library.library.domain;

import com.br.library.library.dtos.BookDtoPost;
import com.br.library.library.dtos.BookDtoPut;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    public Book(BookDtoPost bookDtoPost) {
        this.title = bookDtoPost.getTitle();
        this.genre = bookDtoPost.getGenre();
        this.author = bookDtoPost.getAuthor();
        this.datePublished = bookDtoPost.getDatePublished();
    }
    public Book(BookDtoPut bookDtoPut) {
        this.id = bookDtoPut.getId();
        this.title = bookDtoPut.getTitle();
        this.genre = bookDtoPut.getGenre();
        this.author = bookDtoPut.getAuthor();
        this.datePublished = bookDtoPut.getDatePublished();
        this.available = bookDtoPut.isAvailable();

    }

}
