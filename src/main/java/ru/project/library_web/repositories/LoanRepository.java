package ru.project.library_web.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.project.library_web.models.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {
}
