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

    public List<BookAuthor> getBookAuthors(){
        return this.bookAuthors;
    }

    @JsonManagedReference
    //Список экземпляров книги
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
