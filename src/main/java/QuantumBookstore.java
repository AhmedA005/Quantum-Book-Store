import interfaces.IMailService;
import interfaces.IPurchaseStrategy;
import interfaces.IShippingService;
import models.Book;
import services.MailService;
import services.PurchaseStrategies.EmailPurchaseStrategy;
import services.PurchaseStrategies.NotForSalePurchaseStrategy;
import services.PurchaseStrategies.ShippingPurchaseStrategy;
import services.ShippingService;

import java.util.*;

public class QuantumBookstore {
    private Map<String, Book> inventory;
    private Map<String, IPurchaseStrategy> bookStrategies;

    public QuantumBookstore() {
        inventory = new HashMap<>();
        bookStrategies = new HashMap<>();

        IShippingService shippingService = new ShippingService();
        IMailService mailService = new MailService();

        // Set up strategies with service dependencies
        bookStrategies.put("PaperBook", new ShippingPurchaseStrategy(shippingService));
        bookStrategies.put("EBook", new EmailPurchaseStrategy(mailService));
        bookStrategies.put("DemoBook", new NotForSalePurchaseStrategy());
    }

    public void addBook(Book book) {
        inventory.put(book.getISBN(), book);
        System.out.println("Quantum book store: Book added - " + book.getTitle());
    }

    public List<Book> removeOutdatedBooks(int years) {
        List<Book> outdatedBooks = new ArrayList<>();
        Iterator<Map.Entry<String, Book>> iterator = inventory.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Book> entry = iterator.next();
            Book book = entry.getValue();

            if (book.isOutdated(years)) {
                outdatedBooks.add(book);
                iterator.remove();
            }
        }

        System.out.println("Removed " + outdatedBooks.size() + " outdated books");
        return outdatedBooks;
    }

    public double buyBook(String isbn, int quantity, String email, String address) {
        Book book = inventory.get(isbn);
        if (book == null) {
            throw new RuntimeException("Book with ISBN " + isbn + " not found");
        }

        String bookType = book.getClass().getSimpleName();
        IPurchaseStrategy strategy = bookStrategies.get(bookType);

        double totalAmount = strategy.executePurchase(book, address, email, quantity);
        System.out.println("Quantum book store: Purchase completed for " + book.getTitle());

        return totalAmount;
    }
}