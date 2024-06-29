package com.br.library.library.dtos.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class BookDtoPost {

    @NotBlank
    private String title;

    @NotBlank
    private String genre;

    @NotBlank
    private String author;

    @NotNull
    private LocalDate datePublished;
}
