package ru.project.library_web.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.Reader;

public interface ReaderRepository extends CrudRepository<Reader, Long> {
}
