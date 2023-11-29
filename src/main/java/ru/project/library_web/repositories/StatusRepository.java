package ru.project.library_web.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.Status;

public interface StatusRepository extends CrudRepository<Status, Long> {
}
