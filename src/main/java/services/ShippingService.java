package services;

import interfaces.IShippingService;

public class ShippingService implements IShippingService {
    @Override
    public void ship(String address, int quantity) {
        // no implementation required
        System.out.println("Shipping " + quantity + " books to " + address);
    }
}
