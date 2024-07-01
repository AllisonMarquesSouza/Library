package com.br.library.library.dtos.usuario;

import lombok.Builder;

@Builder
public record AuthenticationDtoPost (String login, String password){

}
