import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface OrderDatabaseIO {

    List<Order> getAllOrdersByCustomer(Customer customer);

    void fillDatabase();

    boolean orderExistsInDatabase(Order order);

    void addOrder(Order order);

    void removeOrder(Order order);


    Collection<Order> getAllOrders();
}
