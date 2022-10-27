import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CustomerTest {
    @Test
    void customerIDTest() {
        Customer customer = createNewCustomer();
        assertNotNull(customer.getCustomerID(), "Wrong ID set for customer!");
    }
    @Test
    void customerNameTest() {
        Customer customer = createNewCustomer();
        assertEquals("Anders Anderson", customer.getName(), "Wrong name!");
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
        customer.changeName("Sven Stevenson");
        assertEquals("Sven Stevenson", customer.getName(), "Wrong name when changed");
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
        assertTrue(customer.compareTo(customer1) > 0);
    }

    private Customer createNewCustomer() {
        return new Customer("Anders Anderson", 52);
    }
}
