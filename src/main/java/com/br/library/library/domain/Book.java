package com.br.library.library.domain;

import com.br.library.library.dtos.book.BookDtoPost;
import com.br.library.library.dtos.book.BookDtoPut;
import com.br.library.library.enums.StatusToReserve;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "author")
    private String author;

    @Column(name = "date_Published")
    private LocalDate datePublished;

    @Column(name = "status_Reserve")
    @Enumerated(EnumType.STRING)
    private StatusToReserve statusToReserve;

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
        this.statusToReserve = bookDtoPut.getStatusToReserve();

    }
    public Book( Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.genre = book.getGenre();
        this.author = book.getAuthor();
        this.datePublished = book.getDatePublished();
        this.statusToReserve = book.getStatusToReserve();
    }

}
