package ru.project.library_web.models.mobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "book_shop")
public class BookShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private AuthorShop author;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private CategoryShop category;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = true)
    private PublisherShop publisher;

    @Column(name = "publication_year")
    private int publication_year;

    @Column(name = "volume")
    private int volume;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Override
    public String toString() {
        return "BookShop{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", publisher=" + publisher +
                ", publication_year=" + publication_year +
                ", volume=" + volume +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
