package com.br.library.library.repository;

import com.br.library.library.domain.Book;
import com.br.library.library.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    Optional<Reservation> findByUsuario(Usuario usuario);
//    Reservation findByUsuario(Usuario usuario);
    Optional<Reservation> findByBook(Book book);

}
