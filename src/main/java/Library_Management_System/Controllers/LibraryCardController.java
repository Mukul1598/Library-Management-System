package Library_Management_System.Controllers;


import Library_Management_System.Services.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class LibraryCardController {

    @Autowired
    private LibraryCardService libraryCardService;

    @PostMapping("/generateCard")
    public ResponseEntity addCard(){
        String result = libraryCardService.generateCard();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestParam("cardId")Integer cardId,
                                                  @RequestParam("rollNo")Integer rollNo){

        String result = libraryCardService.associateCardAndStudent(cardId,rollNo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
