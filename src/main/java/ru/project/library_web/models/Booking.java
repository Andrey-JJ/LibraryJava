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
    @JoinColumn(name = "copybook_id", nullable = true)
    @JsonManagedReference
    private CopyBook copyBook;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = true)
    @JsonManagedReference
    private Reader reader;
}
