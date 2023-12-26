package ru.project.library_web.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = true)
    @JsonManagedReference
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = true)
    @JsonManagedReference
    private Reader reader;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", book=" + book +
                ", reader=" + reader +
                '}';
    }
}
