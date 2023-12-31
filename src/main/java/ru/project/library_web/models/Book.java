package ru.project.library_web.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = true)
    @JsonManagedReference
    private Publisher publisher;

    @Column(name = "publication_year")
    private int publication_year;

    @Column(name = "volume")
    private int volume;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    @JsonManagedReference
    private Category category;
    @JsonBackReference
    @OneToMany(mappedBy = "book")
    private List<BookAuthor> authors;

    @JsonManagedReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CopyBook> copyBooks;

    public void setAuthors(List<BookAuthor> authors) {
        this.authors = authors;
    }

    public List<BookAuthor> getAuthors(){
        return this.authors;
    }

    public List<CopyBook> getCopyBooks() {
        return copyBooks;
    }

    public void setCopyBooks(List<CopyBook> copyBooks) {
        this.copyBooks = copyBooks;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher=" + publisher +
                ", publication_year=" + publication_year +
                ", volume=" + volume +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }

    public Book() {
        this.authors = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImage(final String image) {
        this.image = image;
    }
}
