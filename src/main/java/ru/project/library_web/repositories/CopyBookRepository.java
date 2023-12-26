package ru.project.library_web.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.CopyBook;

public interface CopyBookRepository extends CrudRepository<CopyBook, Long> {
}
