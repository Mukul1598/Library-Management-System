package Library_Management_System.Controllers;


import Library_Management_System.Entities.Book;
import Library_Management_System.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;   // bookService object


    @PostMapping("/addBook")
//  public String addBook(@RequestBody Book book){
//      String result = bookService.addBook(book);
//      return result;
//  }
    public ResponseEntity addBook(@RequestBody Book book){

        try{
            String result = bookService.addBook(book);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/associateBookAndAuthor")
    public String associateBookAndAuthor(@RequestParam("bookId") Integer bookId,
                                         @RequestParam("authorId") Integer authorId){

//        String result = bookService.associateBookAndAuthor(bookId , authorId);
//        return result;

        try{
            String result = bookService.associateBookAndAuthor(bookId , authorId);
            return result;
        } catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("/getBooksByAuthor")
    public List<Book> getBooksByAuthor(@RequestParam("authorId")Integer authorId){
        List<Book> result = bookService.findBooksByAuthor(authorId);
        return result;
    }

}
