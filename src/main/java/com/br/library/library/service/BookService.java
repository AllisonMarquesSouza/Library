package com.br.library.library.service;

import com.br.library.library.domain.Book;
import com.br.library.library.dtos.book.BookDtoPost;
import com.br.library.library.dtos.book.BookDtoPut;
import com.br.library.library.enums.StatusToReserve;
import com.br.library.library.exception.BadRequestException;
import com.br.library.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public List<Book> findAllAvailable() {
        return bookRepository.findAllBooksAvailable();
    }

    public List<Book> findByGenre(String genre) {
        List<Book> byGenreIgnoreCase = bookRepository.findByGenreIgnoreCase(genre);
        if (byGenreIgnoreCase.isEmpty()) {
            throw new EntityNotFoundException("Genre not found");
        }
        return byGenreIgnoreCase;
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found, check the fields"));
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new EntityNotFoundException("Book not found, check the fields"));
    }

    public Book findByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author)
                .orElseThrow(() -> new EntityNotFoundException("Book not found, check the fields"));
    }
    public Book findByTitleAndGenreAndAuthor(String title, String genre, String author) {
        return bookRepository.findByTitleAndGenreAndAuthor(title, genre, author)
                .orElseThrow(() -> new EntityNotFoundException("Book not found, check the fields"));

    }

    @Transactional
    public Book save(BookDtoPost postBook) {

        if(bookRepository.existsByTitleIgnoreCase(postBook.getTitle())) {
            throw new BadRequestException("Book already exists ");
        }
        Book book = new Book(postBook);
        book.setStatusToReserve(StatusToReserve.AVAILABLE);
        return bookRepository.save(book);
    }


    public void update(BookDtoPut bookDtoPut){
        Book bookFound = findById(bookDtoPut.getId());
        Book bookUpdated = new Book(bookDtoPut);
        bookUpdated.setId(bookFound.getId());
        bookRepository.save(bookUpdated);

    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        bookRepository.deleteById(id);
    }
}
