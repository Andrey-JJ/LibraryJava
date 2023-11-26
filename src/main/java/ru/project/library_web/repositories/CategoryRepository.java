package ru.project.library_web.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
