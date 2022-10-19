import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerDataBaseTest {

    private final FakeCustomerDatabase customerDatabase = new FakeCustomerDatabase();
    @Test
    void addCustomersFromDBToList() {
        assertEquals(5,customerDatabase.customerList.size());
        for (Customer c: customerDatabase.customerList) {
            System.out.println(c.getName());
        }
    }
    @Test
    void getCustomerIDTest () {
        for (Customer c : customerDatabase.customerList) {
            System.out.println(c.getCustomerID());
        }
    }
}