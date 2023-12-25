package ru.project.library_web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.*;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByPublisherIsNull();
    List<Book> findByCategoryIsNull();
}
