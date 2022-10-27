import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private UUID custumerID;
    @Test
    void customerIDTest() {
        Customer customer = createNewCustomer();
        assertEquals(true, customer.getCustomerID() instanceof UUID, "Wrong ID set for customer!");
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
    void equalNameEqualsEqualObject() {
        Customer customer = new Customer("Lisa", 27);
        Customer customer1 = new Customer("Lisa", 90);
        assertEquals(customer1, customer, "Object is not equal");
    }

    @Test
    void equalNameEqualsSameHashcode() {
        Customer customer = new Customer("Lisa", 30000);
        Customer customer1 = new Customer("Lisa", 250000);
        assertEquals(customer1.hashCode(), customer.hashCode(), "Not same hashcode");
    }

    @Test
    void compareToReturnPositiveIntForLisaCompareToAnna() {
        Customer customer = new Customer("Lisa", 30000);
        Customer customer1 = new Customer("Anna", 25000);
        assertTrue(customer.compareTo(customer1)>1);
    }

    /*@Test
    void addNewOrderToCustomer() {
        Customer customer = createNewCustomer();
        Order order = new Order(UUID.randomUUID(), new Employee());
        Order order1 = new Order(UUID.randomUUID(), new Employee());
        Order order2 = new Order(UUID.randomUUID(), new Employee());
        customer.addOrder(order);
        customer.addOrder(order1);
        customer.addOrder(order2);
        assertEquals(3,customer.getOrders().size(), "Wrong number of orders!");
    }*/

    private Customer createNewCustomer() {
        custumerID = UUID.randomUUID();
        return new Customer("Anders Andersson", 52);
    }
}
