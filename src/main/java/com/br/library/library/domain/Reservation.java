package com.br.library.library.domain;

import com.br.library.library.dtos.ReservationDtoPost;
import com.br.library.library.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private Book book;

    private LocalDateTime reservationDate;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus statusReservation;

    public Reservation() {

    }
    public Reservation(Usuario usuario, Book book) {
        this.usuario = usuario;
        this.book = book;
        this.reservationDate = LocalDateTime.now();

    }



}
