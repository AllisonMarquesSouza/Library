package com.br.library.library.controller;

import com.br.library.library.domain.Reservation;
import com.br.library.library.dtos.ReservationDtoPost;
import com.br.library.library.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("findById/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> doReservation(@RequestBody @Valid ReservationDtoPost reservationDtoPost) {
        return ResponseEntity.ok(reservationService.doReserve(reservationDtoPost));
    }
 }
