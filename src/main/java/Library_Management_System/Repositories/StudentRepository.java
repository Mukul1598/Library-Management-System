package Library_Management_System.Repositories;


import Library_Management_System.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentByBranchAndCgpaGreaterThan(String branch, double cgpa);

    Student findStudentByEmailId(String emailId);

}
