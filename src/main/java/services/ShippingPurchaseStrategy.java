package services;

import interfaces.IPurchaseStrategy;
import models.Book;
import models.PaperBook;

public class ShippingPurchaseStrategy implements IPurchaseStrategy {
    @Override
    public double executePurchase(Book book, String address, String email, int quantity) {
        if (((PaperBook) book).getQuantity() < quantity) {
            throw new RuntimeException("Required quantity is not available in stock.");
        }
        int currentQuantity = ((PaperBook) book).getQuantity();
        ((PaperBook) book).setQuantity(currentQuantity - quantity);
        return quantity * book.getPrice();
    }
}
