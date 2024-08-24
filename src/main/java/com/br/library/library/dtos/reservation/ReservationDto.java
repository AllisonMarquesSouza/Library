package com.br.library.library.dtos.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReservationDto {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String genre;

    @NotBlank
    private String author;

}
