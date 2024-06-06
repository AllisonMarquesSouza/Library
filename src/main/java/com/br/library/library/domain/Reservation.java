package com.br.library.library.domain;

import com.br.library.library.enums.ReservationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Book book;

    private LocalDateTime reservationDate;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus statusReservation;

    public Reservation() {

    }



}
