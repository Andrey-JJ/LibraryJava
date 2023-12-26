package ru.project.library_web.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.CopyBook;

import java.util.List;

public interface CopyBookRepository extends CrudRepository<CopyBook, Long> {
    List<CopyBook> findByBookIdAndStatusId(Long bookId, Long statusId);
}
