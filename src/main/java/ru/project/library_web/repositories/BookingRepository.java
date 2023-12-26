package ru.project.library_web.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.project.library_web.models.Booking;
import ru.project.library_web.models.Loan;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Loan> {
    @Query("SELECT b FROM Booking b WHERE b.reader.id = :readerId AND b.book.id = :bookId")
    Booking findByReaderIdAndBookId(@Param("readerId") Long readerId, @Param("bookId") Long bookId);

    Optional<Booking> findById(Long id);

    List<Booking> findByReaderId(Long readerId);

    void deleteById(Long id);
}
