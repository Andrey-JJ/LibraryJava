package ru.project.library_web.models;

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

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public String shortName() {
        return lastname + " " + firstname.substring(0,1) + " " + midname.substring(0,1);
    }
    public String fullName() {
        return lastname+" "+firstname+" "+midname;
    }
}