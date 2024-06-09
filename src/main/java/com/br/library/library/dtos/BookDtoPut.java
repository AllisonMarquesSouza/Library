package com.br.library.library.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class BookDtoPut {
    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String genre;

    @NotBlank
    private String author;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate datePublished;

    @NotBlank
    private boolean available;
}
