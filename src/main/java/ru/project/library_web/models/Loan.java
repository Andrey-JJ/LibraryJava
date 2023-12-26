package ru.project.library_web.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
public class Loan {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "copybook_id", nullable = true)
    @JsonBackReference
    private CopyBook copyBook;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = true)
    @JsonBackReference
    private Reader reader;

    @Column(name = "loan_date", nullable = false)
    private Date loan_date;

    @Column(name = "return_date", nullable = true)
    private Date return_date;
}
