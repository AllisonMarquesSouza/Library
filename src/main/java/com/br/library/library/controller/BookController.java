package com.br.library.library.controller;

import com.br.library.library.domain.Book;
import com.br.library.library.dtos.book.BookDtoPost;
import com.br.library.library.dtos.book.BookDtoPut;
import com.br.library.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request, param invalid", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized, the user didn't authenticate", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden, you don't have permission", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})

public class BookController {
    private final BookService bookService;

    @Operation(summary =  "Find all", method = "GET", description ="Find all books",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Operation(summary =  "Find all available", method = "GET", description ="Find all books that's available",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @GetMapping("/findAllAvailable")
    public ResponseEntity<List<Book>> findAllAvailable() {
        return ResponseEntity.ok(bookService.findAllAvailable());
    }

    @Operation(summary =  "Find by id", method = "GET", description ="Find book by id",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @GetMapping("/findById/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Operation(summary =  "Find by genre", method = "GET", description ="Find books by genre",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @GetMapping("/findByGenre/{genre}")
    public ResponseEntity<List<Book>> findByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.findByGenre(genre));
    }

    @Operation(summary =  "Find by title", method = "GET", description ="Find book by title",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<Book> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @Operation(summary =  "Find by author", method = "GET", description ="Find book by author",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @GetMapping("/findByAuthor/{author}")
    public ResponseEntity<Book> findByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.findByAuthor(author));
    }

    @Operation(summary =  "Save book", method = "POST", description ="Save the book",  responses = {
            @ApiResponse(responseCode = "201", description = "successful operation, created", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @PostMapping("/save")
    public ResponseEntity<Book> save(@RequestBody @Valid BookDtoPost bookPost) {
        return new ResponseEntity<>(bookService.save(bookPost), HttpStatus.CREATED);
    }

    @Operation(summary =  "Update book", method = "PUT", description ="Update the book",  responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema())),
    })
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid BookDtoPut bookPut) {
        bookService.update(bookPut);
        return ResponseEntity.ok().build();
    }

    @Operation(summary =  "Delete by id", method = "DELETE", description ="Delete book by id",  responses = {
            @ApiResponse(responseCode = "204", description = "successful operation, no content", content = @Content(schema = @Schema())),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
