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

    //Описание книги
    @Column(name = "description")
    private String description;

    //Категория книги
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    @JsonManagedReference
    private Category category;

    //Список авторов книги
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    //Список экземпляров книги
    @JsonBackReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CopyBook> copyBooks;

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
}
