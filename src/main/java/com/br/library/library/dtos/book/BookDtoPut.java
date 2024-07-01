package com.br.library.library.dtos.book;

import com.br.library.library.enums.StatusToReserve;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@Builder
public class BookDtoPut {
    @NotNull
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String genre;

    @NotBlank
    private String author;

    @NotNull
    private LocalDate datePublished;

    @NotNull
    private StatusToReserve statusToReserve;
}
