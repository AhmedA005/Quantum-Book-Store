package services.PurchaseStrategies;

import interfaces.IPurchaseStrategy;
import models.Book;

public class NotForSalePurchaseStrategy implements IPurchaseStrategy {
    @Override
    public double executePurchase(Book book, String address, String email, int quantity) {
        throw new RuntimeException("Showcase/Demo books are not for sale");
    }
}