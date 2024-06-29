package com.br.library.library.repository;

import com.br.library.library.domain.Book;
import com.br.library.library.domain.Reservation;
import com.br.library.library.domain.Usuario;
import com.br.library.library.dtos.showQueryPersonalized.ShowReservationAndBookDTO;
import com.br.library.library.util.BookCreate;
import com.br.library.library.util.UsuarioCreate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        Book book1 = bookRepository.save(BookCreate.createBook());
        Book book2 = bookRepository.save(BookCreate.createBook2());


        Usuario usuario1 = usuarioRepository.save(UsuarioCreate.createUsuario());
        Usuario usuario2 = usuarioRepository.save(UsuarioCreate.createUsuario2());


        //Make reserve with two book equal, then the most reserved gonna the {book1}
        Reservation save1 = reservationRepository.save(new Reservation(usuario1, book1));
        Reservation save2 = reservationRepository.save(new Reservation(usuario2,book1));
        Reservation save3 = reservationRepository.save(new Reservation(usuario2, book2));


        List<Book> bookMostReserved = reservationRepository.findBookMostReserved();
        log.info(bookMostReserved.toString());

        assertThat(bookMostReserved.size()).isEqualTo(2);

        //checking if the first is the book1
        assertThat(bookMostReserved.get(0).getTitle()).isEqualTo(book1.getTitle());
        assertThat(bookMostReserved.get(0).getGenre()).isEqualTo(book1.getGenre());
        assertThat(bookMostReserved.get(0).getAuthor()).isEqualTo(book1.getAuthor());
        assertThat(bookMostReserved.get(0).getStatusToReserve()).isEqualTo(book1.getStatusToReserve());

        //checking if the second is the book2
        assertThat(bookMostReserved.get(1).getTitle()).isEqualTo(book2.getTitle());
        assertThat(bookMostReserved.get(1).getGenre()).isEqualTo(book2.getGenre());
        assertThat(bookMostReserved.get(1).getAuthor()).isEqualTo(book2.getAuthor());
        assertThat(bookMostReserved.get(1).getStatusToReserve()).isEqualTo(book2.getStatusToReserve());


    }

    @DisplayName("Find reservation by Usuario when is successful")
    @Test
    void findReservationByUsuario() {
        Book book1 = bookRepository.save(BookCreate.createBook());

        Usuario usuario1 = usuarioRepository.save(UsuarioCreate.createUsuario());

        Reservation reservation1 = reservationRepository.save(new Reservation(usuario1, book1));

        List<ShowReservationAndBookDTO> reservationByUsuario =
                reservationRepository.findReservationByUsuario(usuario1.getId());

        log.info(reservationByUsuario.toString());

        assertThat(reservationByUsuario).isNotNull().isNotEmpty();
        assertThat(reservationByUsuario.size()).isEqualTo(1);
        assertThat(reservationByUsuario.get(0).getTitle()).isEqualTo(book1.getTitle());
        assertThat(reservationByUsuario.get(0).getGenre()).isEqualTo(book1.getGenre());
        assertThat(reservationByUsuario.get(0).getAuthor()).isEqualTo(book1.getAuthor());
        assertThat(reservationByUsuario.get(0).getDatePublished()).isEqualTo(book1.getDatePublished());
        assertThat(reservationByUsuario.get(0).getStatusToReserve()).isEqualTo(book1.getStatusToReserve());
        assertThat(reservationByUsuario.get(0).getReservationDate()).isEqualTo(reservation1.getReservationDate());
        assertThat(reservationByUsuario.get(0).getReturnDate()).isEqualTo(reservation1.getReturnDate());


    }
}