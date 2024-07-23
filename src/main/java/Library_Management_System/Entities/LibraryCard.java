package Library_Management_System.Entities;


import Library_Management_System.Enums.LibraryCardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "card_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Enumerated(value = EnumType.STRING) // @Enumerated(EnumType.STRING)
    private LibraryCardStatus libraryCardStatus;

    private Integer noOfBookIssued;

    private Date validity;

    @JoinColumn
    @OneToOne
    private Student student;
}
