package models;

public class EBook extends Book {
    private String fileType;

    public EBook(String ISBN, String title, int publishYear, double price, String fileType) {
        super(ISBN, title, publishYear, price);
        this.fileType = fileType;
    }
}
