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
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "midname")
    private String midname;

    //Список книг автора
    @JsonBackReference
    @OneToMany(mappedBy = "author")
    private List<BookAuthor> bookAuthors;

    @Override
    public String toString() {
        return lastname+" "+firstname+" "+midname;
    }
}