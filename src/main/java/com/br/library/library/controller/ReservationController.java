package com.br.library.library.controller;

import com.br.library.library.domain.Book;
import com.br.library.library.domain.Reservation;
import com.br.library.library.dtos.usuario.AuthenticationDtoPost;
import com.br.library.library.dtos.reservation.ReservationDto;
import com.br.library.library.dtos.showQueryPersonalized.ShowReservationAndBookDTO;
import com.br.library.library.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request, param invalid", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized, the user didn't authenticate", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden, you don't have permission", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})

public class ReservationController {
    private final ReservationService reservationService;
    @Operation(summary =  "Find most reserved ", method = "GET", description ="Find most reserved books",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @GetMapping("/findBooksMostReserved")
    public ResponseEntity<List<Book>> findBooksMostReserved(){
        return ResponseEntity.ok(reservationService.findBooksMostReserved());
    }

    @Operation(summary =  "Find by id", method = "GET", description ="Find reservation by id",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Reservation.class)))
    })
    @GetMapping("/findById/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @Operation(summary =  "Find by user", method = "GET", description ="find reservation by user",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ShowReservationAndBookDTO.class)))
    })
    @GetMapping("/findReservationByUsuario")
    public ResponseEntity<List<ShowReservationAndBookDTO>> findReservationByUsuario(@RequestBody AuthenticationDtoPost usuario) {
        return ResponseEntity.ok(reservationService.findReservationByUsuario(usuario));
    }

    @Operation(summary =  "Make a reservation", method = "POST", description ="Make a reservation",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Reservation.class)))
    })
    @PostMapping("/reserve")
    public ResponseEntity<Reservation> makeReservation(@RequestBody @Valid ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.makeReservation(reservationDto));
    }

    @Operation(summary =  "Return a reservation", method = "PUT", description ="Return book of reservation",  responses = {
            @ApiResponse(responseCode = "204", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @PutMapping("/returnBook")
    public ResponseEntity<Void> returnBook(@RequestBody @Valid ReservationDto reservationDto) {
        reservationService.returnBook(reservationDto);
        return ResponseEntity.noContent().build();
    }
 }
