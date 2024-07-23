package Library_Management_System.Controllers;


import Library_Management_System.Entities.Author;
import Library_Management_System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestBody Author author){

        String result = authorService.addAuthor(author);
        return result;
//      return authorService.addAuthor(author);
    }

    @GetMapping("/getAuthorWithMaxBooks")
    public Author getAuthorWithMaxBooks(){
        Author author = authorService.findAuthorMaxBooks();
        return author;
    }
}
