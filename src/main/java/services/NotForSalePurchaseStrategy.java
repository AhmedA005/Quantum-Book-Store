package services;

import interfaces.IPurchaseStrategy;
import models.Book;

public class NotForSalePurchaseStrategy implements IPurchaseStrategy {
    @Override
    public double executePurchase(Book book, String address, String email, int quantity) {
        throw new RuntimeException("Quantum book store: Showcase books are not for sale");
    }
}