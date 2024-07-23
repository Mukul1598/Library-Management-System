package Library_Management_System.Services;


import Library_Management_System.Entities.Book;
import Library_Management_System.Entities.LibraryCard;
import Library_Management_System.Entities.Transaction;
import Library_Management_System.Enums.TransactionStatus;
import Library_Management_System.Repositories.BookRepository;
import Library_Management_System.Repositories.LibraryCardRepository;
import Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private BookRepository bookRepository;

    public static final Integer Maximum_No_Of_Books = 3;

    public static final Integer Fine_Per_Day = 5;


    public String issueBook(Integer bookId, Integer cardId) throws Exception{

        //Find book and card from repositories
        //Create the transaction Object
        //set the relevant attributes of transactionObject
        //Change the attributes of Card and Book Entity



        //1. Book should be valid

        // Book book = bookRepository.findById(bookId).get();

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new Exception("BookId entered is incorrect");
        }
        Book book = bookOptional.get();



        //2. LibraryCard should be valid

        // LibraryCard card = libraryCardRepository.findById(cardId).get();

        Optional<LibraryCard> libraryCardOptional = libraryCardRepository.findById(cardId);
        if(libraryCardOptional.isEmpty()) {
            throw new Exception("LibraryCardId entered is incorrect");
        }
        LibraryCard libraryCard = libraryCardOptional.get();


        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);
        transaction.setTransactionStatus(TransactionStatus.PENDING);



        //3. Book should not be already issued
        if(book.getIsIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            return "Book is already issued to LibraryCardId" + libraryCard.getCardId();
            //throw new Exception("Book is already issued to LibraryCardId" + libraryCard.getCardNo());

        }


        //4. Card limit has exceeded
        if(libraryCard.getNoOfBookIssued() > Maximum_No_Of_Books){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            return "Maximum limit of this card has exceeded";
            //throw new Exception("Maximum limit of this card has exceeded");
        }


        //5. Check if card has expired
        Long timeOfCardValidityInMilliSec = libraryCard.getValidity().getTime();
        Long currentTimeInMilliSec = System.currentTimeMillis();


        if(currentTimeInMilliSec > timeOfCardValidityInMilliSec){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "The Library Card has expired";
        }


        //Happy flow
        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        book.setIsIssued(true);
        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued() + 1);

        // Rule is whatever has been modified should be saved
        libraryCardRepository.save(libraryCard);
        bookRepository.save(book);
        transaction = transactionRepository.save(transaction); // transactionRepository.save(transaction);

        return "The transaction has been completed with transactionID" + transaction.getTransactionId();
    }

    public String returnBook(Integer cardId, Integer bookId){

        LibraryCard libraryCard = libraryCardRepository.findById(cardId).get();
        Book book = bookRepository.findById(bookId).get();

        Transaction transaction = transactionRepository.findTransactionByBookAndLibraryCardAndTransactionStatus(book,libraryCard,TransactionStatus.ISSUED);

        // Fine Amount
        Long timeDifferenceInMilliSec = System.currentTimeMillis() - transaction.getIssueDate().getTime();
        Long days = TimeUnit.DAYS.convert(timeDifferenceInMilliSec , TimeUnit.MILLISECONDS);

        Integer fineAmount = 0;
        if(days>15){
            fineAmount = Math.toIntExact((days - 15) * Fine_Per_Day);
        }



        // Save transaction
        transaction.setFineAmount(fineAmount);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setReturnDate(new Date());
        book.setIsIssued(Boolean.FALSE);
        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued() - 1);

        transactionRepository.save(transaction);
        bookRepository.save(book);
        libraryCardRepository.save(libraryCard);

        return "The book has been successfully returned";
    }


}
