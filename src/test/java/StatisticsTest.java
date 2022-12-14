import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

public class StatisticsTest extends FakeCheckOutSessionDatabase {

    private Statistics statistics;
    private FakeEmployeeDatabase fakeEmployeeDatabase;
    private FakeOrderDatabase fakeOrderDatabase;
    private FakeCustomerDatabase fakeCustomerDatabase;
    private FakeProductDatabase fakeProductDatabase;
    private FakeCheckOutSessionDatabase fakeCheckOutSessionDatabase;
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;

    @BeforeEach
    void init() {
        fakeEmployeeDatabase = new FakeEmployeeDatabase();
        fakeCustomerDatabase = new FakeCustomerDatabase();
        fakeProductDatabase = new FakeProductDatabase();
        fakeOrderDatabase = new FakeOrderDatabase();
        fakeCheckOutSessionDatabase = new FakeCheckOutSessionDatabase();

        CheckOutSession checkOutSession1 = Mockito.mock(CheckOutSession.class);
        CheckOutSession checkOutSession2 = Mockito.mock(CheckOutSession.class);
        CheckOutSession checkOutSession3 = Mockito.mock(CheckOutSession.class);
        CheckOutSession checkOutSession4 = Mockito.mock(CheckOutSession.class);
        CheckOutSession checkOutSession5 = Mockito.mock(CheckOutSession.class);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession1);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession2);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession3);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession4);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession5);
        employee1 = fakeEmployeeDatabase.getEmployee("Anna");
        employee2 = fakeEmployeeDatabase.getEmployee("Calle");
        employee3 = fakeEmployeeDatabase.getEmployee("Daniella");

        Mockito.when(checkOutSession1.getSessionLengthInSeconds()).thenReturn(1000);
        Mockito.when(checkOutSession2.getSessionLengthInSeconds()).thenReturn(1000);
        Mockito.when(checkOutSession3.getSessionLengthInSeconds()).thenReturn(1000);
        Mockito.when(checkOutSession4.getSessionLengthInSeconds()).thenReturn(2000);
        Mockito.when(checkOutSession5.getSessionLengthInSeconds()).thenReturn(4500);
        Mockito.when(checkOutSession1.getEmployee()).thenReturn(employee1);
        Mockito.when(checkOutSession2.getEmployee()).thenReturn(employee2);
        Mockito.when(checkOutSession3.getEmployee()).thenReturn(employee3);
        Mockito.when(checkOutSession4.getEmployee()).thenReturn(employee3);
        Mockito.when(checkOutSession5.getEmployee()).thenReturn(employee3);

        statistics = new Statistics(fakeEmployeeDatabase, fakeOrderDatabase, fakeProductDatabase, fakeCheckOutSessionDatabase);
    }

    @Test
    void getAverageSalaryReturnsCorrectNumber() {
        assertEquals(30_000, statistics.getAverageSalary());
    }

    @Test
    void getCustomerMostBoughtProductReturnsCorrectProduct() {
        fakeOrderDatabase.fillDatabase();
        Product butter = fakeProductDatabase.getProductFromDatabase("Butter");
        Product mostSold = statistics.getCustomerMostBoughtProduct(fakeCustomerDatabase.getCustomer("Jacob"));
        assertEquals(butter, mostSold);
    }

    @Test
    void getEmployeeAverageCheckOutSessionLengthTest() {
        assertEquals(2500, statistics.getAverageCheckOutSessionLength(employee3, fakeCheckOutSessionDatabase));
    }

    @Test
    void getEmployeesSortedBySpeedTest() {
        fillDatabaseWithStatisticTestData();
        Map<String, Integer> employeesBySpeed = statistics.getEmployeesBySpeed();
        assertEquals("{Daniella=13, Calle=33, Anna=100}", employeesBySpeed.toString());
    }

    @Test
    void PullingTopFiveProductsEverSoldReturnsCorrectResult() {
        fakeOrderDatabase.fillDatabase();
        Map<String, Integer> topFive = statistics.getTopFiveSoldProductsEver();
        assertEquals("{Minced Meat=215, Pasta=47, Butter=47, Tomato=32, Chickpeas=28}", topFive.toString());
    }

   /* @Test
    void PullingTopFiveProductsEverSoldWorksAfterAddingNewOrder(){
        Checkout checkout = new Checkout(fakeCheckOutSessionDatabase,fakeProductDatabase,fakeOrderDatabase,fakeEmployeeDatabase,fakeCustomerDatabase);
        checkout.loginEmployee(fakeEmployeeDatabase.getEmployee("Calle"));
        for (int i = 0; i < 20; i++){
            checkout.scanEAN(917563849363L);
            checkout.scanEAN(925463847583L);
            checkout.scanEAN(917563848693L);
            checkout.scanEAN(917563849363L);
            checkout.scanEAN(928374658273L);
            checkout.scanEAN(917569267583L);
            checkout.scanEAN(917263847583L);
        }
        Order o = checkout.getOrder();
        fakeOrderDatabase.addOrder(checkout.getOrder()); //F??r h??rdkoda detta eftersom det ??r olika instanser av databasen som Checkout och Statistics jobbar mot
        assertEquals("{Minced Meat=235, Tomato=72, Chickpeas=48, Pasta=47, Butter=47}", statistics.getTopFiveSoldProductsEver().toString());
        //fakeOrderDatabase.removeOrder(o);
    }*/

    @Test
    void GetCustomerWhoShoppedMostReturnsCorrectCustomer() {
        fakeOrderDatabase.fillDatabase();
        assertEquals("Theo", statistics.getCustomerWithMostOrders().getKey().getName());
    }
    @Test
    void GetCustomerWhoShoppedMostReturnsCorrectAfterAddingOrder(){
        fakeEmployeeDatabase.fillDatabase();
        fakeOrderDatabase.fillDatabase();
        Order o1 = new Order(fakeEmployeeDatabase.getEmployee("Calle"), fakeCustomerDatabase.getCustomer("Johan"), new OrderLine("Chickpeas", 8.0, 10));
        Order o2 = new Order(fakeEmployeeDatabase.getEmployee("Daniella"), fakeCustomerDatabase.getCustomer("Johan"), new OrderLine("Cucumber", 5.0, 10));
        fakeOrderDatabase.addOrder(o2);
        fakeOrderDatabase.addOrder(o1);
        assertEquals("Johan", statistics.getCustomerWithMostOrders().getKey().getName());
    }

    private void fillDatabaseWithStatisticTestData() {
        OrderLine orderLine1 = new OrderLine("Bananer", 10, 5);
        OrderLine orderLine2 = new OrderLine("Tomater", 30, 5);
        OrderLine orderLine3 = new OrderLine("Russin", 10, 18);
        OrderLine orderLine4 = new OrderLine("Br??d", 30, 20);
        OrderLine orderLine5 = new OrderLine("Saft", 10, 50);
        OrderLine orderLine6 = new OrderLine("Kalaspuffar", 30, 500);
        OrderLine orderLine7 = new OrderLine("Potatis", 5, 12);
        Order order1 = new Order(employee1, orderLine1, orderLine2);
        Order order2 = new Order(employee2, orderLine3);
        Order order3 = new Order(employee3, orderLine4, orderLine5, orderLine6);
        Order order4 = new Order(employee2, orderLine7);
        fakeOrderDatabase.addOrder(order1);
        fakeOrderDatabase.addOrder(order2);
        fakeOrderDatabase.addOrder(order3);
        fakeOrderDatabase.addOrder(order4);
    }

}