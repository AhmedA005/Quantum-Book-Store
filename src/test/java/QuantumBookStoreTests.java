import models.Book;
import models.DemoBook;
import models.EBook;
import models.PaperBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.print.Paper;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuantumBookStoreTests {
    private QuantumBookstore store;
    private PaperBook paperBook;
    private EBook eBook;
    private DemoBook demoBook;

    @BeforeEach
    void setup(){
        store = new QuantumBookstore();
        paperBook = new PaperBook("123", "Java Programming", 2021, 50.0, 10);
        eBook = new EBook("456", "Python Guide", 2022, 30.0, "PDF");
        demoBook = new DemoBook("789", "Demo Book", 2022, 5.0);
    }

    @Test
    void testAddBook(){
        assertDoesNotThrow(() -> store.addBook(paperBook));
        assertDoesNotThrow(() -> store.addBook(eBook));
        assertDoesNotThrow(() -> store.addBook(demoBook));
    }

    @Test
    void testRemoveBook(){
        store.addBook(paperBook);
        store.addBook(eBook);
        PaperBook oldBook1 = new PaperBook("210", "C Programming", 2009, 15.0, 2);
        PaperBook oldBook2 = new PaperBook("471", "Fortran Programming", 2003, 10.0, 1);
        store.addBook(oldBook1);
        store.addBook(oldBook2);

        List<Book> removedBooks = store.removeOutdatedBooks(10);

        assertEquals(2, removedBooks.size());
        assertEquals(oldBook1, removedBooks.get(0));
        assertEquals(oldBook2, removedBooks.get(1));

        // Assert that the oldbook is not in the inventory anymore
        assertThrows(RuntimeException.class, () ->
                store.buyBook("old123", 1, "test@email.com", "123 Main St"));
    }
    @Test
    void testBuyPaperBook(){
        store.addBook(paperBook);
        double totalPrice = store.buyBook("123", 2, "ahmed.ahmedmostafa.h@gmail.com", "Cairo");
        assertEquals(100.0, totalPrice);

        // Verify stock is reduced (original 10 - 2 = 8)
        // We can test this by trying to buy 9 books (should fail)
        assertThrows(RuntimeException.class, () ->
                store.buyBook("123", 9, "ahmed.ahmedmostafa.h@gmail.com", "Cairo"));
    }

    @Test
    void testBuyEBook() {
        store.addBook(eBook);

        double amount = store.buyBook("456", 3, "ahmed.ahmedmostafa.h@gmail.com", "Cairo");

        assertEquals(90.0, amount);

        // Should be able to buy again (no stock limit for ebooks)
        assertDoesNotThrow(() ->
                store.buyBook("456", 3, "ahmed.ahmedmostafa.h@gmail.com", "Cairo"));
    }

    @Test
    void testBuyShowcaseBook() {
        store.addBook(demoBook);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                store.buyBook("789", 1, "ahmed.ahmedmostafa.h@gmail.com", "Cairo"));

        assertTrue(exception.getMessage().contains("Showcase/Demo books are not for sale"));
    }

    @Test
    void testBuyNonexistentBook() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                store.buyBook("999", 1, "ahmed@email.com", "Cairo"));

        assertTrue(exception.getMessage().contains("Book with ISBN 999 not found"));
    }
}
