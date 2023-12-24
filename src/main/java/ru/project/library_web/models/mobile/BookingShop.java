package ru.project.library_web.models.mobile;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.library_web.models.Reader;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking_shop")
public class BookingShop {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = true)
    private BookShop book;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = true)
    private Reader reader;
}
