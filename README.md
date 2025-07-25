# Quantum-Book-Store
Quantum Bookstore
A Java-based online bookstore system that manages different types of books with various purchase behaviors.

## Features

- Inventory Management: Add and remove books from store inventory
- Multiple Book Types:
  - Paper Books (physical shipping required).
  - E-Books (email delivery).
  - Demo Books (showcase only, not for sale)



- **Smart Purchase System**: Different handling for each book type.
- **Outdated Book Removal**: Automatically remove books older than specified years.


### Book Types

| Type       | Features  | Purchase Behavior  |
|------------|---|---|
| Paper Book |  Has stock quantity | Ships to address via ShippingService  |
| E-Book     |  Has file type (PDF, EPUB, etc.) | Sent via email using MailService  |
| Demo Book  |  Display only | Cannot be purchased  |

### Core Operations:
- **addBook()** - Add books to inventory.
- **removeOutdatedBooks(years)** - Remove books older than specified years.
- **buyBook(isbn, quantity, email, address)** - Purchase books with appropriate delivery.

### Architecture

Built using **Strategy Pattern** for extensibility:

1. Easy to add new book types
2. Clean separation of purchase behaviors
3. SOLID principles compliance

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── interfaces/         # Service and strategy interfaces
│   │   ├── models/             # Book classes (Book, EBook, PaperBook, DemoBook)
│   │   ├── services/           # Business logic and strategy implementations
│   │   └── QuantumBookstore    # Main application class
│   └── resources/              # Configuration and static resources
└── test/
    └── java/
        └── QuantumBookStoreTests # Unit tests
```

### How to Run
1. Compile all Java files
2. Navigate to QuantumBookstoreTests.java
3. Right-click the file and select Run to execute all tests

###### _**Make sure JUnit is set up in your project dependencies.**_

### Example Usage

```java
QuantumBookstore store = new QuantumBookstore();

// Add books
store.addBook(new PaperBook("123", "Java Programming", 2021, 50.0, 10));
store.addBook(new EBook("456", "Python Guide", 2022, 30.0, "PDF"));

// Buy a book
double amount = store.buyBook("123", 2, "ahmed@gmail.com", "Cairo");
// Output: Shipping service called + amount returned

// Remove old books
List<Book> removed = store.removeOutdatedBooks(10);
```

### Extensibility

To add a new book type:

1. Create new book class extending Book
2. Create corresponding PurchaseStrategy implementation
3. Register strategy in QuantumBookstore constructor
4. No changes needed to existing code!