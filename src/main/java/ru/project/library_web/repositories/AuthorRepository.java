package ru.project.library_web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
