package Library_Management_System.Controllers;


import Library_Management_System.Entities.Student;
import Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
//          OR
//      String result = studentService.addStudent(student);
//      return result;
    }

    @GetMapping("/getTopperStudent")
    public List<Student> getTopperStudent(@RequestParam("branch")String branch,
                                          @RequestParam("cgpa")double cgpa){
        List<Student> ansList = studentService.getTopperStudent(branch, cgpa);
        return ansList;
    }
}
