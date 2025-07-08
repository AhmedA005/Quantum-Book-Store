package models;

public class PaperBook extends Book {
    private int quantity;
    public PaperBook(String ISBN, String title, int publishYear, double price, int quantity) {
        super(ISBN, title, publishYear, price);
        this.quantity = quantity;
    }
}
