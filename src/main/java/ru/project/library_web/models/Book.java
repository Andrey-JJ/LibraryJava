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

    //Наименование
    @Column(name = "title")
    private String title;

    //Издатель
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = true)
    @JsonManagedReference
    private Publisher publisher;

    //Год издания
    @Column(name = "publication_year")
    private int publication_year;

    //Объем книг - Кол-во страниц
    @Column(name = "volume")
    private int volume;

    //Изображение
    @Column(name = "image")
    private String image;

    public void setImage(final String image) {
        this.image = image;
    }

    //Описание книги
    @Column(name = "description")
    private String description;

    //Категория книги
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    @JsonManagedReference
    private Category category;

    //Список авторов книги
    // В классе Book
    @JsonBackReference
    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthors;

    public void setBookAuthors(List<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public List<BookAuthor> getBookAuthors(){
        return this.bookAuthors;
    }

    @JsonManagedReference
    //Список экземпляров книги
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CopyBook> copyBooks;

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
        this.bookAuthors = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
