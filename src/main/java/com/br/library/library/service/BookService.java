package com.br.library.library.service;

import com.br.library.library.domain.Book;
import com.br.library.library.dtos.BookPostBody;
import com.br.library.library.dtos.BookPutBody;
import com.br.library.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Book save(BookPostBody postBook) {
        Book book = new Book(postBook);
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    public Book replace(BookPutBody bookPutBody){
        findById(bookPutBody.getId());
        Book book = new Book(bookPutBody);
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(findById(id).getId());
    }
}
