import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerDataBaseTest {

    private final FakeCustomerDatabase customerDatabase = new FakeCustomerDatabase();
    @Test
    void addCustomersFromDBToList() {
        for (Customer c: customerDatabase.customerList) {
            System.out.println("Customer: " + c.getName() +" "+ "CustomerID: "+ c.getCustomerID());
            assertEquals(5,customerDatabase.customerList.size());
        }
    }
}