package com.br.library.library.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReservationDtoPost {
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
