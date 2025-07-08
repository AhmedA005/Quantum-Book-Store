package interfaces;

public interface IShippingService {
    public void ship(String info, int quantity); // info could be the address or the email, depending on the service
}
