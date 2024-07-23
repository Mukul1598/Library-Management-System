package Library_Management_System.Services;


import Library_Management_System.Entities.Author;
import Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author){

        author.setAuthorNoOfBooks(0); // Setting the default value as 0 instead of null
        authorRepository.save(author);
        return "Author has been saved to the database";
    }

    public Author findAuthorMaxBooks(){
        List<Author> authorList = authorRepository.findAll();
        int maxBooks = 0;
        Author ansAuthor = null;
        for(Author author : authorList){
            if(author.getAuthorNoOfBooks() > maxBooks){
                maxBooks = author.getAuthorNoOfBooks();
                ansAuthor = author;
            }
        }
        return ansAuthor;
    }

}
