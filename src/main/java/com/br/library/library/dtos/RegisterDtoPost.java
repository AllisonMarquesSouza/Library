package com.br.library.library.dtos;

import com.br.library.library.enums.UserRole;

public record RegisterDtoPost (String login, String password, String email, UserRole role) {
}
