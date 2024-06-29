package com.br.library.library.repository;

import com.br.library.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCase(String title);
    List<Book> findByGenreIgnoreCase(String genre);
    boolean existsByTitleIgnoreCase(String title);
    Optional<Book> findByAuthorIgnoreCase(String author);
    Optional<Book> findByTitleAndGenreAndAuthor(String title, String genre, String author);

    @Query(""" 
        SELECT b
            FROM Book b
         WHERE b.statusToReserve = 'AVAILABLE'
        """)
    List<Book> findAllStatusToReserveIsAVAILABLE();









}

