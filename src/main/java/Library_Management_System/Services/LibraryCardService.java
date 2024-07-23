package Library_Management_System.Services;


import Library_Management_System.Entities.LibraryCard;
import Library_Management_System.Entities.Student;
import Library_Management_System.Enums.LibraryCardStatus;
import Library_Management_System.Repositories.LibraryCardRepository;
import Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LibraryCardService {

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String generateCard(){

        LibraryCard card = new LibraryCard();
        card.setLibraryCardStatus(LibraryCardStatus.NEW);
        card.setNoOfBookIssued(0);

        Date expiryDate = new Date(128,6,1);
        card.setValidity(expiryDate);

        card = libraryCardRepository.save(card);

        return "The Library Card has been generated with cardID " + card.getCardId();
    }

    public String associateCardAndStudent(Integer cardId, Integer rollNo){
        LibraryCard libraryCard = libraryCardRepository.findById(cardId).get();

        Student student = studentRepository.findById(rollNo).get();

        libraryCard.setLibraryCardStatus(LibraryCardStatus.ISSUED);

        // Set the student for the library card
        libraryCard.setStudent(student);

        // Save the updated library card
        libraryCardRepository.save(libraryCard);

        return "The student and the card has been associated ";
    }


}
