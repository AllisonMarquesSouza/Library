package com.br.library.library.util;

import com.br.library.library.domain.Reservation;
import com.br.library.library.dtos.reservation.ReservationDto;
import com.br.library.library.dtos.showQueryPersonalized.ShowReservationAndBookDTO;
import com.br.library.library.dtos.usuario.AuthenticationDtoPost;
import com.br.library.library.enums.StatusToReserve;

import java.time.LocalDate;

public class ReservationCreate {

    public static Reservation createReservationValid(){
        return Reservation.builder()
                .id(1L)
                .book(BookCreate.createBookValid())
                .usuario(UsuarioCreate.createUsuario())
                .reservationDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(10))
                .build();
    }
    public static ReservationDto createReservationBookReserved(){
        return ReservationDto.builder()
                .title(BookCreate.createBook().getTitle())
                .genre(BookCreate.createBook().getGenre())
                .author(BookCreate.createBook().getAuthor())
                .login(UsuarioCreate.createUsuario().getLogin())
                .password(UsuarioCreate.createUsuario().getPassword())
                .email(UsuarioCreate.createUsuario().getEmail())
                .build();
    }

    public static ReservationDto createReservationDtoValid(){
        return ReservationDto.builder()
                .title(BookCreate.createBookValid().getTitle())
                .genre(BookCreate.createBookValid().getGenre())
                .author(BookCreate.createBookValid().getAuthor())
                .login(UsuarioCreate.createUsuario().getLogin())
                .password(UsuarioCreate.createUsuario().getPassword())
                .email(UsuarioCreate.createUsuario().getEmail())
                .build();
    }
    public static ShowReservationAndBookDTO createShowReservationAndBookDTO(){
        return ShowReservationAndBookDTO.builder()
                .title("Shadow Slave")
                .genre("Romance")
                .author("Guilt333")
                .datePublished(LocalDate.of(2020, 5, 20))
                .statusToReserve(StatusToReserve.AVAILABLE)
                .reservationDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(10))
                .build();
    }
    public static AuthenticationDtoPost createAuthenticationDto(){
        return AuthenticationDtoPost.builder()
                .login(UsuarioCreate.createUsuario().getLogin())
                .password(UsuarioCreate.createUsuario().getPassword())
                .build();
    }
}
