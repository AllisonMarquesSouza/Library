package com.br.library.library.repository;

import com.br.library.library.domain.Book;
import com.br.library.library.domain.Reservation;
import com.br.library.library.domain.Usuario;
import com.br.library.library.util.BookCreate;
import com.br.library.library.util.UsuarioCreate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@Log4j2
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    //Execute test one for one , because could give an error
    @DisplayName("Find reservation most reserved when is successful")
    @Test
    void findBookMostReserved() {
        Book book1 = bookRepository.save(BookCreate.createBookAvailable());
        Book book2 = bookRepository.save(BookCreate.createBookAvailable2());


        Usuario usuario1 = usuarioRepository.save(UsuarioCreate.createUsuario());
        Usuario usuario2 = usuarioRepository.save(UsuarioCreate.createUsuario2());

        reservationRepository.save(new Reservation(usuario1, book1));
        reservationRepository.save(new Reservation(usuario2,book1));
        reservationRepository.save(new Reservation(usuario2, book2));


        List<Book> bookMostReserved = reservationRepository.findBookMostReserved();

        assertEquals(bookMostReserved.size(), 2);
        assertEquals(bookMostReserved.get(0), book1);
        assertEquals(bookMostReserved.get(1), book2);

    }

    @DisplayName("Find reservation by Usuario when is successful")
    @Test
    void findReservationByUsuario() {
        Usuario usuario = usuarioRepository.save(UsuarioCreate.createUsuario());
        Book book1 = bookRepository.save(BookCreate.createBookAvailable());

        Reservation reservation = reservationRepository.save(new Reservation(usuario, book1));
        reservation.setId(1L);

        List<Reservation> reservationByUsuario =
                reservationRepository.findReservationByUsuarioId(1L);

        assertNotNull(reservationByUsuario);
        assertEquals(reservationByUsuario.size(), 1);
        assertEquals(reservationByUsuario.get(0), reservation);

    }
}