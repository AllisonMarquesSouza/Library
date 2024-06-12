package com.br.library.library.service;

import com.br.library.library.domain.Book;
import com.br.library.library.dtos.BookDtoPost;
import com.br.library.library.dtos.BookDtoPut;
import com.br.library.library.exception.BadRequestException;
import com.br.library.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public List<Book> findAllAvailableIsTrue() {
        return bookRepository.findAllByAvailableIsTrue();
    }

    public List<Book> findByGenre(String genre) {
        List<Book> byGenreIgnoreCase = bookRepository.findByGenreIgnoreCase(genre);
        if (byGenreIgnoreCase.isEmpty()) {
            throw new EntityNotFoundException("Genre not exist");
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

    @Transactional
    public Book save(BookDtoPost postBook) {
        if(bookRepository.existsByTitleIgnoreCase(postBook.getTitle())) {
            throw new BadRequestException("Book already exists ");
        }
        Book book = new Book(postBook);
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    @Transactional
    public Book replace(BookDtoPut bookDtoPut){
        findById(bookDtoPut.getId());
        Book book = new Book(bookDtoPut);
        return bookRepository.save(book);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(findById(id).getId());
    }
}
