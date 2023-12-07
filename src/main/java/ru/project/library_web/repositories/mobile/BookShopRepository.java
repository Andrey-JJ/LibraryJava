package ru.project.library_web.repositories.mobile;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.mobile.BookShop;

public interface BookShopRepository extends CrudRepository<BookShop, Long> {
}
