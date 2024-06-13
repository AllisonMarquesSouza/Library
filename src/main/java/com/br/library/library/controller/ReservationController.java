package com.br.library.library.controller;

import com.br.library.library.domain.Reservation;
import com.br.library.library.dtos.AuthenticationDtoPost;
import com.br.library.library.dtos.ReservationDtoPost;
import com.br.library.library.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }
    @GetMapping("/findByUsuario")
    public ResponseEntity<List<Reservation>> findByUsuario(@RequestBody AuthenticationDtoPost usuario) {
        return ResponseEntity.ok(reservationService.findByUsuario(usuario));
    }

    @PostMapping("/reserve")
    public ResponseEntity<Reservation> doReservation(@RequestBody @Valid ReservationDtoPost reservationDtoPost) {
        return ResponseEntity.ok(reservationService.doReserve(reservationDtoPost));
    }

    @PutMapping("/returnBook")
    public ResponseEntity<Void> removeReservation(@RequestBody @Valid ReservationDtoPost reservationDtoPost) {
        reservationService.removeReserve(reservationDtoPost);
        return ResponseEntity.noContent().build();
    }
 }
