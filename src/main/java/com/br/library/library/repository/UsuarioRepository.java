package com.br.library.library.repository;

import com.br.library.library.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
    Optional<Usuario> findByEmail(String email);
}
