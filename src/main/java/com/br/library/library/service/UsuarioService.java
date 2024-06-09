package com.br.library.library.service;

import com.br.library.library.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public void checkExistUserByEmail(String email) {
        boolean result = usuarioRepository.existsByEmail(email);

        if (!result) {
            throw new EntityNotFoundException("User not Found");
        }

    }

}
