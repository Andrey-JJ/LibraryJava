package ru.project.library_web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
