import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    @Test
    void customerIDTest() {
        Customer customer = createNewCustomer();
        assertEquals(123, customer.getCustomerID(), "Wrong ID set for customer!");
    }
    @Test
    void customerNameTest() {
        Customer customer = createNewCustomer();
        assertEquals("Anders Andersson", customer.getName(), "Wrong name!");
    }
    @Test
    void customerAgeTest() {
        Customer customer = createNewCustomer();
        customer.increaseAge();
        customer.increaseAge();
        assertEquals(54, customer.getAge(), "Wrong age set or not increasing by 1");
    }

    @Test
    void changeNameTest() {
        Customer customer = createNewCustomer();
        customer.changeName("Sven Svensson");
        assertEquals("Sven Svensson", customer.getName(), "Wrong name when changed");
    }

    @Test
    void addNewOrderToCustomer() {
        Customer customer = createNewCustomer();
        Order order = new Order();
        Order order1 = new Order();
        Order order2 = new Order();
        customer.addOrder(order);
        customer.addOrder(order1);
        customer.addOrder(order2);
        assertEquals(3,customer.getOrders().size(), "Wrong number of orders!");
    }

    private Customer createNewCustomer() {
        return new Customer(123, "Anders Andersson", 52);
    }
}
