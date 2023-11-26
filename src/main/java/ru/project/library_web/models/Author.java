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
    private Long id;
    private String lastname;
    private String firstname;
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
