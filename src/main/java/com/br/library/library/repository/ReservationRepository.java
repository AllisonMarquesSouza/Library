package com.br.library.library.repository;

import com.br.library.library.domain.Book;
import com.br.library.library.domain.Reservation;
import com.br.library.library.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByBookAndUsuario(Book book, Usuario usuario);

    @Query("""
        SELECT b
            FROM Book b
          WHERE b.id IN (SELECT r.book.id FROM Reservation r GROUP BY r.book.id  ORDER BY COUNT(*) DESC)
           ORDER BY ( SELECT COUNT(*) FROM Reservation ordinate WHERE ordinate.book.id = b.id) DESC
        """)
    List<Book> findBookMostReserved();


    @Query("""
        SELECT r
            FROM Reservation AS r
         WHERE r.usuario.id = :usuarioId
        """)
    List<Reservation> findReservationByUsuarioId(Long usuarioId);

}
