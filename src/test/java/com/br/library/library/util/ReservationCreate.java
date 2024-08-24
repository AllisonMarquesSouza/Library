package com.br.library.library.util;

import com.br.library.library.domain.Reservation;

import java.time.LocalDate;

public class ReservationCreate {

    public static Reservation createReservationValid(){
        return Reservation.builder()
                .id(1L)
                .book(BookCreate.createBookAvailable())
                .usuario(UsuarioCreate.createUsuario())
                .reservationDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(10))
                .build();
    }

}
