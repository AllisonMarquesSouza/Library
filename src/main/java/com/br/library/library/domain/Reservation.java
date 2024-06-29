package com.br.library.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    @Column(nullable = false)
    private LocalDate reservationDate;

    private LocalDate returnDate;


    public Reservation() {

    }
    public Reservation(Usuario usuario, Book book) {
        this.usuario = usuario;
        this.book = book;
        this.reservationDate = LocalDate.now();
    }


}
