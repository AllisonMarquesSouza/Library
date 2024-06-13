package com.br.library.library.domain;

import com.br.library.library.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    private LocalDate reservationDate;
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus currentStatusReservation;

    public Reservation() {

    }
    public Reservation(Usuario usuario, Book book) {
        this.usuario = usuario;
        this.book = book;

    }



}
