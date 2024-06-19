package com.br.library.library.controller;

import com.br.library.library.domain.Book;
import com.br.library.library.dtos.BookDtoPost;
import com.br.library.library.dtos.BookDtoPut;
import com.br.library.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }
    @GetMapping("/findAllAvailable")
    public ResponseEntity<List<Book>> findAllAvailableIsTrue() {
        return ResponseEntity.ok(bookService.findAllAvailableIsTrue());
    }

    @GetMapping("/findByGenre/{genre}")
    public ResponseEntity<List<Book>> findByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.findByGenre(genre));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<Book> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @GetMapping("/findByAuthor/{author}")
    public ResponseEntity<Book> findByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.findByAuthor(author));
    }

    @PostMapping("/save")
    public ResponseEntity<Book> save(@RequestBody @Valid BookDtoPost bookPost) {
        return new ResponseEntity<>(bookService.save(bookPost), HttpStatus.CREATED);
    }

    @PutMapping("/replace")
    public ResponseEntity<Book> replace(@RequestBody @Valid BookDtoPut bookPut) {
        return new ResponseEntity<>(bookService.replace(bookPut), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
