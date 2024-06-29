package com.br.library.library.domain;

import com.br.library.library.dtos.book.BookDtoPost;
import com.br.library.library.dtos.book.BookDtoPut;
import com.br.library.library.enums.StatusToReserve;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDate datePublished;

    @Column(nullable = false)
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
