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
    private Long id;

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

    @Override
    public String toString() {
        return "CopyBook{" +
                "id=" + id +
                ", book=" + book +
                ", status=" + status +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
