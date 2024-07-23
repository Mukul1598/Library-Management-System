package Library_Management_System.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "author_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically updates the id
    private Integer authorId;

    private String authorName;

    private Integer authorAge;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int authorNoOfBooks;

//    private String bookRating;

    private String authorEmail;



}
