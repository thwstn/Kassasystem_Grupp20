import java.util.ArrayList;
import java.util.UUID;

public interface OrderDatabaseIO {

    ArrayList<Order> getAllOrdersByCustomer(Customer customer);

    void fillDatabase();
}
