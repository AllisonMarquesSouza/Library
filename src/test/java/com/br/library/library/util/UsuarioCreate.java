package com.br.library.library.util;

import com.br.library.library.domain.Usuario;
import com.br.library.library.enums.UserRole;

public class UsuarioCreate {

    public static Usuario createUsuario() {
        return Usuario.builder()
                .id(1L)
                .login("allison")
                .password("123456")
                .email("allison10@gmail.com")
                .role(UserRole.ADMIN)
                .build();

    }
    public static Usuario createUsuario2() {
        return Usuario.builder()
                .id(1L)
                .login("thefool")
                .password("123456")
                .email("thefool@gmail.com")
                .role(UserRole.ADMIN)
                .build();

    }
}
