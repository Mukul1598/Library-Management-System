package Library_Management_System.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity // This is the structure/schema of Student Table
@Table(name = "student_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo;

    @Column(nullable = false)   // Name cannot be null value
    private String name;

    private Integer age;

    private String branch;

    @Column(nullable = false)
    private Double cgpa;

    private String year;

    @Column(unique = true)
    private String emailId;
    
}
