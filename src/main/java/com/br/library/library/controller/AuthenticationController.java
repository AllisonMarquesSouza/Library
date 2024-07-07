package com.br.library.library.controller;

import com.br.library.library.domain.Usuario;
import com.br.library.library.dtos.jwtToken.DtoJWTToken;
import com.br.library.library.dtos.usuario.AuthenticationDtoPost;
import com.br.library.library.dtos.usuario.RegisterDtoPost;
import com.br.library.library.enums.UserRole;
import com.br.library.library.exception.BadRequestException;
import com.br.library.library.methodsToCheckThings.CheckThingsIFIsCorrect;
import com.br.library.library.repository.UsuarioRepository;
import com.br.library.library.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request, param invalid", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized, the user didn't authenticate", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden, you don't have permission", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})

public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final CheckThingsIFIsCorrect checkThingsIFIsCorrect;


    @Operation(summary =  "Login", method = "POST", description ="Make login with User", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = DtoJWTToken.class)))
    })
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

    @Operation(summary =  "Make register", method = "POST", description ="Make register of User",  responses = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(schema = @Schema(implementation = Usuario.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody @Valid RegisterDtoPost registerDto) {
        if(usuarioRepository.findByLogin(registerDto.login()) != null) {
            throw new BadRequestException("User with login already exists");
        } else if(usuarioRepository.findByEmail(registerDto.email()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        checkThingsIFIsCorrect.checkEmailIsOk(registerDto.email());

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

        Usuario usuario = new Usuario(registerDto.login(), encryptedPassword, registerDto.email());
        usuario.setRole(UserRole.USER);

        return new ResponseEntity<>(usuarioRepository.save(usuario) , HttpStatus.CREATED);

    }


}
