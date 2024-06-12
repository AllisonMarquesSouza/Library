package com.br.library.library.controller;

import com.br.library.library.domain.Usuario;
import com.br.library.library.dtos.AuthenticationDtoPost;
import com.br.library.library.dtos.DtoJWTToken;
import com.br.library.library.dtos.RegisterDtoPost;
import com.br.library.library.repository.UsuarioRepository;
import com.br.library.library.security.TokenService;
import com.br.library.library.methodsToCheckThings.CheckThingsIFIsCorrect;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager; //form of authenticate user with spring
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDtoPost data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        if (auth.isAuthenticated()) {
            return ResponseEntity.ok(new DtoJWTToken(token));
        } else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDtoPost registerDto) {
        if(usuarioRepository.findByLogin(registerDto.login()) != null ) {
            return ResponseEntity.badRequest().build();
        }
        CheckThingsIFIsCorrect.checkEmailIsOk(registerDto.email());

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

        Usuario usuario = new Usuario(registerDto.login(), encryptedPassword, registerDto.email(), registerDto.role());

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();

    }


}
