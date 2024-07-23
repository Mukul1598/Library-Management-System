package Library_Management_System.Entities;


import Library_Management_System.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(unique = true)
    private String bookTitle;

    @Enumerated(value = EnumType.STRING) // @Enumerated(EnumType.STRING)
    private Genre genre;

    private Integer bookRating;

    private Integer noOfPages;

    private Boolean isIssued;

    @JoinColumn
    @ManyToOne
    private Author author;

}
