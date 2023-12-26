package ru.project.library_web.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
public class Loan {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "copybook_id", nullable = true)
    @JsonBackReference
    private CopyBook copyBook;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = true)
    @JsonBackReference
    private Reader reader;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "loan_date", nullable = false)
    private Date loan_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "return_date", nullable = true)
    private Date return_date;

    public Date getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(Date loan_date) {
        this.loan_date = loan_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", copyBook=" + copyBook +
                ", reader=" + reader +
                ", loan_date=" + loan_date +
                ", return_date=" + return_date +
                '}';
    }
}
