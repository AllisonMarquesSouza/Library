package com.br.library.library.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Getter
public class BookPostBody {

    @NotBlank
    private String title;

    @NotBlank
    private String genre;

    @NotBlank
    private String author;

    @NotBlank
    private LocalDateTime publisLocalDateTime;
}
