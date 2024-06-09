package com.br.library.library.service;

import com.br.library.library.domain.Book;
import com.br.library.library.domain.Reservation;
import com.br.library.library.domain.Usuario;
import com.br.library.library.dtos.ReservationDtoPost;
import com.br.library.library.exception.BadRequestException;
import com.br.library.library.repository.ReservationRepository;
import com.br.library.library.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.br.library.library.enums.ReservationStatus.RESERVED;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookService bookService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
    }


    public Reservation doReserve(ReservationDtoPost reservationPost) {

        usuarioRepository.existsByLogin(reservationPost.getLogin().describeConstable()
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!")));

        Usuario usuario = usuarioRepository.findByLogin(reservationPost.getLogin());

        Book book = bookService.findByTitle(reservationPost.getTitle());


        if(book.isAvailable()) {
            Reservation reservation = new Reservation(usuario, book);

            reservation.getBook().setAvailable(false);
            reservation.setStatusReservation(RESERVED);

            return reservationRepository.save(reservation);
        }
        throw new BadRequestException("Book is not available");
    }

    public void removeReserve(Long id) {

    }

}
