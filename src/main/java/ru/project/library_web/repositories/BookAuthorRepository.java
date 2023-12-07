package ru.project.library_web.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.BookAuthor;

public interface BookAuthorRepository extends CrudRepository<BookAuthor, Long> {
}
