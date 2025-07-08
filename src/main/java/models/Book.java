package models;

public abstract class Book {
   protected String ISBN;
   protected String title;
   protected int publishYear;
   protected double price;

    public Book(String ISBN, String title, int publishYear, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.publishYear = publishYear;
        this.price = price;
    }
}
