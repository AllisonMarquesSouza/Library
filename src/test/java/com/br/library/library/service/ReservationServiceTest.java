package com.br.library.library.service;

import com.br.library.library.domain.Book;
import com.br.library.library.domain.Reservation;
import com.br.library.library.domain.Usuario;
import com.br.library.library.dtos.reservation.ReservationDto;
import com.br.library.library.enums.StatusToReserve;
import com.br.library.library.exception.BadRequestException;
import com.br.library.library.methodsToCheckThings.CheckThingsIFIsCorrect;
import com.br.library.library.repository.ReservationRepository;
import com.br.library.library.repository.UsuarioRepository;
import com.br.library.library.util.BookCreate;
import com.br.library.library.util.ReservationCreate;
import com.br.library.library.util.UsuarioCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private BookService bookService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private CheckThingsIFIsCorrect checkThingsIFIsCorrect;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        Usuario usuario = UsuarioCreate.createUsuario();
        Book book = BookCreate.createBookAvailable();
        Book bookReserved = BookCreate.createBookReserved();
        Reservation reservation = ReservationCreate.createReservationValid();

        Mockito.when(usuarioRepository.findByLoginAndEmail("allison", "allison@gmail.com"))
                .thenReturn(Optional.of(usuario));

        Mockito.when(usuarioRepository.findByLogin("allison"))
                .thenReturn(usuario);

        Mockito.when(usuarioRepository.findByEmail("allison@gmail.com"))
                .thenReturn(Optional.of(usuario));

        Mockito.when(bookService.findByTitle("lord")).thenReturn(book);

        Mockito.when(bookService.findByTitleAndGenreAndAuthor("lord", "Romance", "me"))
                .thenReturn(bookReserved);

        Mockito.when(reservationRepository.findByBookAndUsuario(bookReserved, usuario))
                .thenReturn(Optional.of(reservation));

        Mockito.doNothing().when(checkThingsIFIsCorrect)
                .checkPasswordIsOk(Mockito.anyString(), Mockito.anyString());

    }

    @Test
    @DisplayName("Should find reservation by ID when is successful")
    void findById() {
        Mockito.when(reservationRepository.findById(1L))
                .thenReturn(Optional.of(ReservationCreate.createReservationValid()));

        Reservation expectedReservation = ReservationCreate.createReservationValid();
        Reservation reservationByID = reservationService.findById(1L);

        assertNotNull(reservationByID);
        assertEquals(reservationByID.getUsuario(), expectedReservation.getUsuario());
        assertEquals(reservationByID.getBook(), expectedReservation.getBook());
        assertEquals(reservationByID.getReservationDate(), expectedReservation.getReservationDate());
        assertEquals(reservationByID.getReturnDate(), expectedReservation.getReturnDate());
    }


    @Test
    @DisplayName("Should find reservation most reserved when is successful")
    void findBooksMostReserved() {
        Mockito.when(reservationRepository.findBookMostReserved())
                .thenReturn(List.of(BookCreate.createBookAvailable()));

        Book expectedBook = BookCreate.createBookAvailable();
        List<Book> booksMostReserved = reservationService.findBooksMostReserved();

        assertNotNull(booksMostReserved);
        assertEquals(booksMostReserved.size(), 1);
        assertEquals(booksMostReserved.get(0), expectedBook);
    }

    @Test
    @DisplayName("Should find reservation by Usuario  when is successful")
    void findReservationByUsuario() {
        Reservation expectedReservation = ReservationCreate.createReservationValid();

        Mockito.when(reservationRepository.findReservationByUsuarioId(Mockito.anyLong()))
                .thenReturn(List.of(expectedReservation));

        List<Reservation> reservationByUsuario = reservationService
                .findReservationByUsuario("allison", "123456");

        assertNotNull(reservationByUsuario);
        assertEquals(reservationByUsuario.size(), 1);
        assertEquals(reservationByUsuario.get(0).getUsuario(), expectedReservation.getUsuario());
        assertEquals(reservationByUsuario.get(0).getBook(), expectedReservation.getBook());
        assertEquals(reservationByUsuario.get(0).getReservationDate(), expectedReservation.getReservationDate());
        assertEquals(reservationByUsuario.get(0).getReturnDate(), expectedReservation.getReturnDate());
    }

    @Test
    @DisplayName("Should throw BadRequestException when reservation by Usuario id not found")
    void findReservationByUsuarioCaseErrorNotFound() {
        Mockito.when(reservationRepository.findReservationByUsuarioId(1L))
                .thenThrow(new BadRequestException("You don't have any reservations yet"));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> reservationService.findReservationByUsuario("allison", "123456")
        );

        assertSame(exception.getClass(), BadRequestException.class);
        assertEquals("You don't have any reservations yet", exception.getMessage());
    }

    @Test
    @DisplayName("Should make reservation when is successful")
    void makeReservation() {
        ReservationDto reservationDto = new ReservationDto("allison", "123456",
                "allison@gmail.com", "lord", "romance", "me");

        Book book = BookCreate.createBookAvailable();
        Usuario usuario = UsuarioCreate.createUsuario();
        Reservation expectedReservation = new Reservation(usuario, book);

        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class)))
                .thenReturn(expectedReservation);

        Reservation reservation = reservationService.makeReservation(reservationDto);

        assertNotNull(reservation);
        assertEquals(reservation.getUsuario(), expectedReservation.getUsuario());
        assertEquals(reservation.getBook(), expectedReservation.getBook());
        assertEquals(reservation.getReservationDate(), expectedReservation.getReservationDate());
        assertEquals(reservation.getReturnDate(), expectedReservation.getReturnDate());
    }


    @Test
    @DisplayName("Should throw BadRequestException when book not available is successful")
    void makeReservationWhenBadRequestException() {
        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class)))
                .thenThrow(new BadRequestException("Reservation failed"));

        ReservationDto reservationDto = new ReservationDto("allison", "123456",
                "allison@gmail.com", "lord", "romance", "me");

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> reservationService.makeReservation(reservationDto)
        );

        assertSame(exception.getClass(), BadRequestException.class);
        assertEquals("Reservation failed", exception.getMessage());
    }

    @Test
    @DisplayName("Should return book of reservation when is successful")
    void returnBook() {
        Usuario usuario = UsuarioCreate.createUsuario();
        Book book = BookCreate.createBookReserved();
        book.setStatusToReserve(StatusToReserve.AVAILABLE);
        Reservation reservation = new Reservation(usuario, book);

        ReservationDto reservationDto = new ReservationDto("allison", "123456",
                "allison@gmail.com", "lord", "Romance", "me");

        ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);

        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class)))
                .thenReturn(reservation);

        assertDoesNotThrow(() -> reservationService.returnBook(reservationDto));

        verify(reservationRepository).save(reservationCaptor.capture());

        Reservation savedReservation = reservationCaptor.getValue();

        assertEquals(reservation.getUsuario(), savedReservation.getUsuario());
        assertEquals(reservation.getBook(), savedReservation.getBook());
    }

    @Test
    @DisplayName("Should throw BadRequestException when book not reserved is successful")
    void returnBookWhenTheBookIsNotReserved() {
        ReservationDto reservationDto = new ReservationDto("allison", "123456",
                "allison@gmail.com", "lord", "Romance", "me");

        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class)))
                .thenThrow(new BadRequestException("Reservation failed"));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> reservationService.returnBook(reservationDto)
        );

        assertSame(exception.getClass(), BadRequestException.class);
        assertEquals("Reservation failed", exception.getMessage());

    }
}