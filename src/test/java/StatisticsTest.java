import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

public class StatisticsTest extends FakeCheckOutSessionDatabase {

    FakeEmployeeDatabase fakeEmployeeDatabase;
    Statistics statistics;
    FakeOrderDatabase fakeOrderDatabase;
    FakeCustomerDatabase fakeCustomerDatabase;
    FakeProductDatabase fakeProductDatabase;
    FakeCheckOutSessionDatabase fakeCheckOutSessionDatabase;
    CheckOutSession checkOutSession1;
    CheckOutSession checkOutSession2;
    CheckOutSession checkOutSession3;
    CheckOutSession checkOutSession4;
    CheckOutSession checkOutSession5;
    Employee employee1;
    Employee employee2;
    Employee employee3;

    @BeforeEach
    void init() {
        fakeEmployeeDatabase = new FakeEmployeeDatabase();
        fakeCustomerDatabase = new FakeCustomerDatabase();
        fakeProductDatabase = new FakeProductDatabase();
        fakeOrderDatabase = new FakeOrderDatabase();
        fakeCheckOutSessionDatabase = new FakeCheckOutSessionDatabase();

        checkOutSession1 = Mockito.mock(CheckOutSession.class);
        checkOutSession2 = Mockito.mock(CheckOutSession.class);
        checkOutSession3 = Mockito.mock(CheckOutSession.class);
        checkOutSession4 = Mockito.mock(CheckOutSession.class);
        checkOutSession5 = Mockito.mock(CheckOutSession.class);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession1);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession2);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession3);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession4);
        fakeCheckOutSessionDatabase.addCheckOutSession(checkOutSession5);
        employee1 = fakeEmployeeDatabase.getEmployee("Anna");
        employee2 = fakeEmployeeDatabase.getEmployee("Calle");
        employee3 = fakeEmployeeDatabase.getEmployee("Daniella");

        Mockito.when(checkOutSession1.getSessionLenghtInSeconds()).thenReturn(1000);
        Mockito.when(checkOutSession2.getSessionLenghtInSeconds()).thenReturn(1000);
        Mockito.when(checkOutSession3.getSessionLenghtInSeconds()).thenReturn(1000);
        Mockito.when(checkOutSession4.getSessionLenghtInSeconds()).thenReturn(2000);
        Mockito.when(checkOutSession5.getSessionLenghtInSeconds()).thenReturn(4500);
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
    void getCustomerMostSold() {
        fakeOrderDatabase.fillDatabase();
        Product pasta = fakeProductDatabase.getProductFromDatabase("Butter");
        System.out.println(pasta);
        Product p = statistics.getCustomerMostSold(fakeCustomerDatabase.getCustomer("Jacob"));
        assertEquals(pasta, p);
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

    /*@Test
    void PullingTopFiveProductsEverSoldWorksAfterAddingNewOrder(){
        Checkout c = new Checkout();
        c.loginEmployee(fakeEmployeeDatabase.getEmployee("Calle"));
        for (int i = 0; i < 20; i++){
            c.scanEAN(917563849363L);
            c.scanEAN(925463847583L);
            c.scanEAN(917563848693L);
            c.scanEAN(917563849363L);
            c.scanEAN(928374658273L);
            c.scanEAN(917569267583L);
            c.scanEAN(917263847583L);
        }
        Order o = c.getOrder();
        statistics.fakeOrderDatabase.addOrder(c.getOrder()); //Får hårdkoda detta eftersom det är olika instanser av databasen som Checkout och Statistics jobbar mot
        assertEquals("{Minced Meat=235, Tomato=72, Chickpeas=48, Pasta=47, Butter=47}", statistics.getTopFiveSoldProductsEver().toString());
        statistics.fakeOrderDatabase.removeOrder(o);
    }*/

    @Test
    void GetCustomerWhoShoppedMostReturnsCorrectCustomer() {
        fakeOrderDatabase.fillDatabase();
        assertEquals("Theo", statistics.getCustomerWithMostOrders().getKey().getName());
    }
    /*@Test
    void GetCustomerWhoShoppedMostReturnsCorrectAfterAddingOrder(){
        Order o1 = new Order(fakeEmployeeDatabase.getEmployee("Calle"), fakeCustomerDatabase.getCustomer("Johan"), new OrderLine("Chickpeas", 8.0, 10));
        Order o2 = new Order(fakeEmployeeDatabase.getEmployee("Daniella"), fakeCustomerDatabase.getCustomer("Johan"), new OrderLine("Cucumber", 5.0, 10));
        statistics.fakeOrderDatabase.addOrder(o2);
        statistics.fakeOrderDatabase.addOrder(o1);
        assertEquals("Johan", statistics.getCustomerWithMostOrders().getKey().getName());
        statistics.fakeOrderDatabase.removeOrder(o1);
        statistics.fakeOrderDatabase.removeOrder(o2);
    }*/

    private void fillDatabaseWithStatisticTestData() {
        OrderLine orderLine1 = new OrderLine("Bananer", 10, 5);
        OrderLine orderLine2 = new OrderLine("Tomater", 30, 5);
        OrderLine orderLine3 = new OrderLine("Russin", 10, 18);
        OrderLine orderLine4 = new OrderLine("Bröd", 30, 20);
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