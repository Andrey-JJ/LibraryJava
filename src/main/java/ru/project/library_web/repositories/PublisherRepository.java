package ru.project.library_web.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
