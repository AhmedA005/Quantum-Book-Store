package services;

import interfaces.IMailService;

public class MailService implements IMailService {
    @Override
    public void sendEmail(String email, String fileType) {
        // no implementation required
        System.out.println("Sending " + fileType + " file to " + email);
    }
}