package Library_Management_System.Services;


import Library_Management_System.Entities.Student;
import Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student){
        studentRepository.save(student);
        return "Student has been saved to the database";
    }

    public List<Student> getTopperStudent(String branch, double cgpa){
        List<Student> ansList = studentRepository.findStudentByBranchAndCgpaGreaterThan(branch, cgpa);
        return ansList;
    }
}
