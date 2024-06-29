package com.br.library.library.dtos.usuario;

import lombok.Builder;

@Builder
public record RegisterDtoPost (String login, String password, String email) {
}
