package Library_Management_System.Repositories;




import Library_Management_System.Entities.Book;
import Library_Management_System.Entities.LibraryCard;
import Library_Management_System.Entities.Transaction;
import Library_Management_System.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Transaction findTransactionByBookAndLibraryCardAndTransactionStatus(Book book, LibraryCard libraryCard, TransactionStatus transactionStatus);
}
