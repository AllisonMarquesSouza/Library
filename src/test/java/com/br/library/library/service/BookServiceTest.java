package com.br.library.library.service;

import com.br.library.library.domain.Book;
import com.br.library.library.dtos.book.BookDtoPost;
import com.br.library.library.enums.StatusToReserve;
import com.br.library.library.exception.BadRequestException;
import com.br.library.library.repository.BookRepository;
import com.br.library.library.util.BookCreate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@Log4j2
class BookServiceTest {

    @Mock
    private  BookRepository bookRepositoryMock;


    @InjectMocks
    private  BookService bookService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(bookService.findAll()).thenReturn(List.of(BookCreate.createBookValid()));

        BDDMockito.when(bookRepositoryMock.findAllStatusToReserveIsAVAILABLE())
                .thenReturn(List.of(BookCreate.createBookValid()));

        BDDMockito.when(bookRepositoryMock.findByGenreIgnoreCase(ArgumentMatchers.anyString()))
                .thenReturn(List.of(BookCreate.createBookValid()));

        BDDMockito.when(bookRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(BookCreate.createBookValid()));

        BDDMockito.when(bookRepositoryMock.findByTitleIgnoreCase(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(BookCreate.createBookValid()));

        BDDMockito.when(bookRepositoryMock.findByAuthorIgnoreCase(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(BookCreate.createBookValid()));


        BDDMockito.when(bookRepositoryMock.save(ArgumentMatchers.any(Book.class)))
                .thenReturn(BookCreate.createBookValid());

        BDDMockito.doNothing().when(bookRepositoryMock)
                .deleteById(ArgumentMatchers.anyLong());

    }


    @Test
    @DisplayName("Should find all Books when is successful")
    void findAll() {
        List<Book> books = bookService.findAll();

        Book expectedBook = BookCreate.createBookValid();

        assertThat(books).isNotEmpty().isNotNull();
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(books.get(0).getAuthor()).isEqualTo(expectedBook.getAuthor());
        assertThat(books.get(0).getGenre()).isEqualTo(expectedBook.getGenre());

    }
    @Test
    @DisplayName("Should find all books AVAILABLE when is successful")
    void findAllAvailable() {
        List<Book> booksAvailable = bookService.findAllAvailable();

        Book expectedBook = BookCreate.createBookValid();

        assertThat(booksAvailable).isNotEmpty().isNotNull();
        assertThat(booksAvailable).hasSize(1);
        assertThat(booksAvailable.get(0).getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(booksAvailable.get(0).getAuthor()).isEqualTo(expectedBook.getAuthor());
        assertThat(booksAvailable.get(0).getGenre()).isEqualTo(expectedBook.getGenre());
        assertThat(booksAvailable.get(0).getStatusToReserve()).isEqualTo(StatusToReserve.AVAILABLE);

    }

    @Test
    @DisplayName("Should find books by Genre when is successful")
    void findByGenre() {
        List<Book> bookByGenre = bookService.findByGenre("anyString");

        Book expectedBook = BookCreate.createBookValid();

        assertThat(bookByGenre).isNotEmpty().isNotNull();
        assertThat(bookByGenre).hasSize(1);
        assertThat(bookByGenre.get(0).getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(bookByGenre.get(0).getAuthor()).isEqualTo(expectedBook.getAuthor());
        assertThat(bookByGenre.get(0).getGenre()).isEqualTo(expectedBook.getGenre());
    }

    @Test
    @DisplayName("Should find book by Id when is successful")
    void findById() {
        Book bookServiceById = bookService.findById(1L);

        Book expectedBook = BookCreate.createBookValid();

        assertThat(bookServiceById).isNotNull();
        assertThat(bookServiceById.getId()).isEqualTo(expectedBook.getId());
        assertThat(bookServiceById.getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(bookServiceById.getAuthor()).isEqualTo(expectedBook.getAuthor());
        assertThat(bookServiceById.getGenre()).isEqualTo(expectedBook.getGenre());

    }

    @Test
    @DisplayName("Should find book by Title when is successful")
    void findByTitle() {
        Book bookByTitle = bookService.findByTitle("anyString");
        Book expectedBook = BookCreate.createBookValid();

        assertThat(bookByTitle).isNotNull();
        assertThat(bookByTitle.getId()).isEqualTo(expectedBook.getId());
        assertThat(bookByTitle.getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(bookByTitle.getAuthor()).isEqualTo(expectedBook.getAuthor());
        assertThat(bookByTitle.getGenre()).isEqualTo(expectedBook.getGenre());
        
    }

    @Test
    @DisplayName("Should find book by Author when is successful")
    void findByAuthor() {
        Book bookByAuthor = bookService.findByAuthor("anyString");
        Book expectedBook = BookCreate.createBookValid();

        assertThat(bookByAuthor).isNotNull();
        assertThat(bookByAuthor.getId()).isEqualTo(expectedBook.getId());
        assertThat(bookByAuthor.getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(bookByAuthor.getAuthor()).isEqualTo(expectedBook.getAuthor());
        assertThat(bookByAuthor.getGenre()).isEqualTo(expectedBook.getGenre());
    }

    @Test
    @DisplayName("Save Book and return when is successful")
    void save() {
        Book bookToBeSave = bookService.save(BookDtoPost.builder().build());
        Book expectedBook = new Book(BookCreate.createBookValid());

        assertThat(bookToBeSave).isNotNull();
        assertThat(bookToBeSave.getId()).isEqualTo(expectedBook.getId());
        assertThat(bookToBeSave.getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(bookToBeSave.getAuthor()).isEqualTo(expectedBook.getAuthor());
        assertThat(bookToBeSave.getGenre()).isEqualTo(expectedBook.getGenre());
        assertThat(bookToBeSave.getStatusToReserve()).isEqualTo(StatusToReserve.AVAILABLE);


    }
    @Test
    @DisplayName("Should return BadRequestException when title already exist")
    void saveWhenTitleAlreadyExist() {

        BDDMockito.when(bookRepositoryMock.existsByTitleIgnoreCase(ArgumentMatchers.any()))
                .thenReturn(true);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> bookService.save(BookDtoPost.builder().build())
        );

        assertEquals("Book already exists ", exception.getMessage());

    }

    @Test
    @DisplayName("Update Book when is successful")
    void update() {
        assertThatCode(() -> bookService.update(BookCreate.createBookDtoPut()))
        .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Delete Book when is successful")
    void delete() {
        assertThatCode(() -> bookService.delete(1L))
                .doesNotThrowAnyException();
    }
}