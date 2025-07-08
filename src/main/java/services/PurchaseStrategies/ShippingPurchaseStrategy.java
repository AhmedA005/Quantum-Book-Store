package services.PurchaseStrategies;

import interfaces.IPurchaseStrategy;
import interfaces.IShippingService;
import models.Book;
import models.PaperBook;

public class ShippingPurchaseStrategy implements IPurchaseStrategy {
    private IShippingService shippingService;

    public ShippingPurchaseStrategy(IShippingService shippingService) {
        this.shippingService = shippingService;
    }
    @Override
    public double executePurchase(Book book, String address, String email, int quantity) {
        if (((PaperBook) book).getQuantity() < quantity) {
            throw new RuntimeException("Required quantity is not available in stock.");
        }
        int currentQuantity = ((PaperBook) book).getQuantity();
        ((PaperBook) book).setQuantity(currentQuantity - quantity);

        shippingService.ship(address, quantity);
        return quantity * book.getPrice();
    }
}
