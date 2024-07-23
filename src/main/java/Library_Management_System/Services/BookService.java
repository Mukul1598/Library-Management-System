package Library_Management_System.Services;


import Library_Management_System.Entities.Author;
import Library_Management_System.Entities.Book;
import Library_Management_System.Exceptions.InvalidPageCountException;
import Library_Management_System.Repositories.AuthorRepository;
import Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private AuthorRepository authorRepository;


    public String addBook(Book book) throws Exception{

        if(book.getNoOfPages() <= 0){
            throw new InvalidPageCountException("Page count entered is invalid");
        }
        book.setIsIssued(Boolean.FALSE);
        bookRepository.save(book);
        return "Book has been saved to the database";
    }


    public String associateBookAndAuthor(Integer bookId, Integer authorId) throws Exception{

//        Book book = bookRepository.findById(bookId).get();

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new Exception("Book entered is incorrect");
        }
        Book book = bookOptional.get();



//        Author author = authorRepository.findById(authorId).get();

        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(authorOptional.isEmpty()){
            throw new Exception("Author entered is incorrect");
        }
        Author author = authorOptional.get();



        // Associate Book and Author
        book.setAuthor(author);
        author.setAuthorNoOfBooks(author.getAuthorNoOfBooks() + 1);

        bookRepository.save(book);
        authorRepository.save(author);

        return "Book and Author have been associated";
    }


    public List<Book> findBooksByAuthor(Integer authorId){
        List<Book> allBooks = bookRepository.findAll();

        // Need to iterate over allBooks where book.getAuthor().getId() is Matching
        List<Book> result = new ArrayList<>();

        for(Book book : allBooks){
            if(book.getAuthor().getAuthorId().equals(authorId)){ // Book Entity--->Author Entity--->AuthorId
                result.add(book);
            }
        }
        return result;
    }


}
