package com.br.library.library.service;

import com.br.library.library.domain.Book;
import com.br.library.library.domain.Reservation;
import com.br.library.library.domain.Usuario;
import com.br.library.library.dtos.usuario.AuthenticationDtoPost;
import com.br.library.library.dtos.reservation.ReservationDto;
import com.br.library.library.dtos.showQueryPersonalized.ShowReservationAndBookDTO;
import com.br.library.library.exception.BadRequestException;
import com.br.library.library.methodsToCheckThings.CheckThingsIFIsCorrect;
import com.br.library.library.repository.BookRepository;
import com.br.library.library.repository.ReservationRepository;
import com.br.library.library.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.br.library.library.enums.StatusToReserve.*;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookService bookService;
    private final UsuarioRepository usuarioRepository;
    private final BookRepository bookRepository;


    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
    }
    public List<Book> findBooksMostReserved(){
        return reservationRepository.findBookMostReserved();
    }

    public List<ShowReservationAndBookDTO> findReservationByUsuario(AuthenticationDtoPost authentication) {
        Usuario usuario = usuarioRepository.findByLogin(authentication.login());

        if(Objects.isNull(usuario)) {
            throw new EntityNotFoundException("Usuario not found");
        }

        CheckThingsIFIsCorrect.checkPasswordIsOk(authentication.password(), usuario.getPassword());

        return reservationRepository.findReservationByUsuario(usuario.getId());
    }

    @Transactional
    public Reservation doReserve(ReservationDto reservationPost) {

        Usuario userByLogin = usuarioRepository.findByLogin(reservationPost.getLogin()
                .describeConstable()
                .orElseThrow(() -> new EntityNotFoundException("Login not found, check the field")));

        Usuario userByEmail = usuarioRepository.findByEmail(reservationPost.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Email not found, check the field"));

        if(!Objects.equals(userByEmail, userByLogin)){
            throw new IllegalArgumentException("Fields of the User aren't the corrects , check it");
        }

        CheckThingsIFIsCorrect.checkPasswordIsOk(reservationPost.getPassword(), userByLogin.getPassword());


        Book book = bookService.findByTitle(reservationPost.getTitle());

        if(book.getStatusToReserve() == RESERVED || book.getStatusToReserve() == CANCELED ){
            throw new BadRequestException("Book is not available ");
        }

        book.setStatusToReserve(RESERVED);
        Reservation reservation = new Reservation(userByLogin, book);
        return reservationRepository.save(reservation);

    }

    @Transactional
    public void returnBook(ReservationDto reservationPost) {

        Book book = bookRepository
                .findByTitleAndGenreAndAuthor(reservationPost.getTitle(), reservationPost.getGenre(), reservationPost.getAuthor())
                .orElseThrow(() -> new EntityNotFoundException("Book not found, check the field are corrects"));

        Usuario usuario = usuarioRepository
                .findByLoginAndEmail(reservationPost.getLogin(), reservationPost.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found, check the fields are corrects"));


        Reservation reservation = reservationRepository.findByBookAndUsuario(book, usuario)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found, check the user and book"));

        CheckThingsIFIsCorrect.checkPasswordIsOk(reservationPost.getPassword(), usuario.getPassword());

        if(book.getStatusToReserve() == RESERVED ){
            book.setStatusToReserve(AVAILABLE);
            reservation.setReturnDate(LocalDate.now());

        } else
            throw new BadRequestException("Do you reserved the book ? Check it , maybe you returned it");

    }



}
