package com.br.library.library.domain;

import com.br.library.library.dtos.BookPostBody;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private String author;

    private LocalDateTime publisLocalDateTime;

    private boolean available;

    public Book(BookPostBody bookPostBody) {
        this.title = bookPostBody.getTitle();
        this.genre = bookPostBody.getGenre();
        this.author = bookPostBody.getAuthor();
        this.publisLocalDateTime = bookPostBody.getPublisLocalDateTime();
    }

}
