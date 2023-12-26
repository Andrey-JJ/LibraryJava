package ru.project.library_web.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "copybook")
public class CopyBook {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = true)
    @JsonBackReference
    private Book book;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true)
    @JsonManagedReference
    private Status status;

    @JsonBackReference
    @OneToMany(mappedBy = "copyBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans;
}
