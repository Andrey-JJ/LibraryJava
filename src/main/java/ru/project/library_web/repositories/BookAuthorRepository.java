package ru.project.library_web.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.BookAuthor;

import java.util.List;

public interface BookAuthorRepository extends CrudRepository<BookAuthor, Long> {
    List<BookAuthor> findByBookId(Long id);
}
