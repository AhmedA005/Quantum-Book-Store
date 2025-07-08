package interfaces;

import models.Book;

public interface IPurchaseStrategy {
    public double executePurchase(Book book, String address, String email, int quantity);
}
