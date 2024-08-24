package com.br.library.library.service;

import com.br.library.library.domain.Book;
import com.br.library.library.dtos.book.BookDtoPost;
import com.br.library.library.dtos.book.BookDtoPut;
import com.br.library.library.enums.StatusToReserve;
import com.br.library.library.exception.BadRequestException;
import com.br.library.library.repository.BookRepository;
import com.br.library.library.util.BookCreate;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@Log4j2
class BookServiceTest {

    @Mock
    private  BookRepository bookRepositoryMock;


    @InjectMocks
    private  BookService bookService;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("Should find all Books when is successful")
    void findAll() {
        Mockito.when(bookService.findAll()).thenReturn(List.of(BookCreate.createBookAvailable()));

        Book expectedBook = BookCreate.createBookAvailable();
        List<Book> books = bookService.findAll();

        assertNotNull(books);
        assertEquals(books.size(), 1);
        assertEquals(books.get(0), expectedBook);

    }
    @Test
    @DisplayName("Should find all books AVAILABLE when is successful")
    void findAllAvailable() {
        Mockito.when(bookRepositoryMock.findAllBooksAvailable())
                .thenReturn(List.of(BookCreate.createBookAvailable()));

        Book expectedBook = BookCreate.createBookAvailable();
        List<Book> booksAvailable = bookService.findAllAvailable();

        assertNotNull(booksAvailable);
        assertEquals(booksAvailable.size(), 1);
        assertEquals(booksAvailable.get(0), expectedBook);
    }

    @Test
    @DisplayName("Should find books by Genre when is successful")
    void findByGenre() {
        Mockito.when(bookRepositoryMock.findByGenreIgnoreCase("Romance"))
                .thenReturn(List.of(BookCreate.createBookAvailable()));

        Book expectedBook = BookCreate.createBookAvailable();
        List<Book> bookByGenre = bookService.findByGenre("Romance");

        assertNotNull(bookByGenre);
        assertEquals(bookByGenre.size(), 1);
        assertEquals(bookByGenre.get(0), expectedBook);
    }
    @Test
    @DisplayName("Should return EntityNotFoundException when genre not found")
    void findByGenreCaseErrorNotFound() {
        Mockito.when(bookRepositoryMock.findByGenreIgnoreCase("Romance"))
                .thenThrow(new EntityNotFoundException("Genre not found"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> bookService.findByGenre("Romance")
        );

        assertSame(exception.getClass(), EntityNotFoundException.class);
        assertEquals("Genre not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should find book by Id when is successful")
    void findById() {
        Mockito.when(bookRepositoryMock.findById(1L))
                .thenReturn(Optional.of(BookCreate.createBookAvailable()));

        Book expectedBook = BookCreate.createBookAvailable();
        Book bookServiceById = bookService.findById(1L);

        assertNotNull(bookServiceById);
        assertEquals(bookServiceById, expectedBook);
    }

    @Test
    @DisplayName("Should find book by Title when is successful")
    void findByTitle() {
        Mockito.when(bookRepositoryMock.findByTitleIgnoreCase("Lord of the Mystery"))
                .thenReturn(Optional.of(BookCreate.createBookAvailable()));

        Book expectedBook = BookCreate.createBookAvailable();
        Book bookByTitle = bookService.findByTitle("Lord of the Mystery");

        assertNotNull(bookByTitle);
        assertEquals(bookByTitle, expectedBook);
    }

    @Test
    @DisplayName("Should find book by Author when is successful")
    void findByAuthor() {
        Mockito.when(bookRepositoryMock.findByAuthorIgnoreCase(Mockito.anyString()))
                .thenReturn(Optional.of(BookCreate.createBookAvailable()));

        Book expectedBook = BookCreate.createBookAvailable();
        Book bookByAuthor = bookService.findByAuthor("anyString");

        assertNotNull(bookByAuthor);
        assertEquals(bookByAuthor, expectedBook);
    }

    @Test
    @DisplayName("Save Book and return when is successful")
    void save() {
        Mockito.when(bookRepositoryMock.save(Mockito.any(Book.class)))
                .thenReturn(BookCreate.createBookAvailable());

        Book expectedBook = new Book(BookCreate.createBookAvailable());
        Book bookToBeSave = bookService.save(BookDtoPost.builder().build());

        assertNotNull(bookToBeSave);
        assertEquals(bookToBeSave, expectedBook);
    }
    @Test
    @DisplayName("Should return BadRequestException when title already exist")
    void saveCaseErrorAlreadyExist() {
        Mockito.when(bookRepositoryMock.save(Mockito.any(Book.class)))
                .thenThrow(new BadRequestException("Book already exists "));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bookService.save(BookDtoPost.builder().build())
        );

        assertSame(exception.getClass(), BadRequestException.class);
        assertEquals("Book already exists ", exception.getMessage());

    }

    @Test
    @DisplayName("Should update Book when is successful")
    void update() {
        Book book = BookCreate.createBookAvailable();
        BookDtoPut bookPut = new BookDtoPut(1L, "Shadow Slave", "Romance", "Guilt333", LocalDate.now(), StatusToReserve.AVAILABLE);

        Mockito.when(bookRepositoryMock.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(BookCreate.createBookAvailable()));

        Mockito.when(bookRepositoryMock.save(Mockito.any(Book.class))).thenReturn(book);

        assertDoesNotThrow(() -> bookService.update(bookPut));
        verify(bookRepositoryMock).save(book);
    }

    @Test
    @DisplayName("Should delete Book by ID when is successful")
    void delete() {
        Book book = BookCreate.createBookAvailable();

        Mockito.when(bookRepositoryMock.findById(1L))
                .thenReturn(Optional.of(book));

        assertDoesNotThrow(() -> bookService.delete(1L));
        verify(bookRepositoryMock).deleteById(1L);

    }
}