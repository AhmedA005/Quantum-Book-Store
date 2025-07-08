package services.PurchaseStrategies;

import interfaces.IPurchaseStrategy;
import interfaces.IMailService;
import models.Book;
import models.EBook;

public class EmailPurchaseStrategy implements IPurchaseStrategy {
    private IMailService mailService;

    public EmailPurchaseStrategy(IMailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public double executePurchase(Book book, String address, String email, int quantity) {
        String fileType = ((EBook) book).getFileType();
        mailService.sendEmail(email, fileType);
        return quantity * book.getPrice();
    }
}