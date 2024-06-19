package com.br.library.library.dtos.dtoForQueryPersonalized;


import java.time.LocalDate;

public interface ReservationBookDTO {
    String getTitle();
    String getGenre();
    String getAuthor();
    LocalDate getDatePublished();
    String getCurrentStatusReservation();
    LocalDate getReservationDate();
    LocalDate getReturnDate();

}