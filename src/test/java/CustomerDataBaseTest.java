import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDataBaseTest {

    private final FakeCustomerDatabase customerDatabase = new FakeCustomerDatabase();
    @Test
    void addCustomersToList() {
        customerDatabase.fillDB();
        assertEquals(5,customerDatabase.customerList.size());
        for (Customer c: customerDatabase.customerList) {
            System.out.println(c.getName());
        }
    }
    @Test
    void getCustomerIDTest () {
        customerDatabase.fillDB();
        for (Customer c : customerDatabase.customerList) {
            System.out.println(c.getCustomerID());
        }
    }
}