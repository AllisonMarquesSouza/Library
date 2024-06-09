package com.br.library.library.service;

import com.br.library.library.domain.Book;
import com.br.library.library.dtos.BookDtoPost;
import com.br.library.library.dtos.BookDtoPut;
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
    public List<Book> findByGenre(String genre) {
        List<Book> byGenreIgnoreCase = bookRepository.findByGenreIgnoreCase(genre);
        if (byGenreIgnoreCase.isEmpty()) {
            throw new EntityNotFoundException("Genre not exist");
        }
        return byGenreIgnoreCase;
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    @Transactional
    public Book save(BookDtoPost postBook) {
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
