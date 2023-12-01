package ru.project.library_web.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
