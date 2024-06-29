package com.br.library.library.dtos.showQueryPersonalized;


import com.br.library.library.enums.StatusToReserve;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class ShowReservationAndBookDTO {
    private String title;
    private String genre;
    private String author;
    private LocalDate datePublished;
    private StatusToReserve statusToReserve;
    private LocalDate reservationDate;
    private LocalDate returnDate;

}