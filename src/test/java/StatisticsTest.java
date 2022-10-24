import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
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
    ArrayList<CheckOutSession> checkOutSessionsA;
    Employee employee1;
    Employee employee2;
    Employee employee3;

    @BeforeEach
        void init() {
        fakeEmployeeDatabase = new FakeEmployeeDatabase();
        statistics = new Statistics();
        fakeCustomerDatabase = new FakeCustomerDatabase();
        fakeProductDatabase = new FakeProductDatabase();
        fakeOrderDatabase = new FakeOrderDatabase();
        fakeCheckOutSessionDatabase = Mockito.mock(FakeCheckOutSessionDatabase.class);

        checkOutSession1 = Mockito.mock(CheckOutSession.class);
        checkOutSession2 = Mockito.mock(CheckOutSession.class);
        checkOutSession3 = Mockito.mock(CheckOutSession.class);
        employee1 = fakeEmployeeDatabase.getEmployee("Anna");
        employee2 = fakeEmployeeDatabase.getEmployee("Calle");
        employee3 = fakeEmployeeDatabase.getEmployee("Daniella");
        Mockito.when(checkOutSession1.getSessionLenghtInSeconds()).thenReturn(28800);
        Mockito.when(checkOutSession1.getEmployee()).thenReturn(employee1);
        Mockito.when(checkOutSession2.getSessionLenghtInSeconds()).thenReturn(14400);
        Mockito.when(checkOutSession2.getEmployee()).thenReturn(employee2);
        Mockito.when(checkOutSession3.getSessionLenghtInSeconds()).thenReturn(7200);
        Mockito.when(checkOutSession3.getEmployee()).thenReturn(employee3);

        checkOutSessionsA = new ArrayList<>();
        checkOutSessionsA.add(checkOutSession1);
        checkOutSessionsA.add(checkOutSession2);
        checkOutSessionsA.add(checkOutSession3);


        //Mockito.when(fakeCheckOutSessionDatabase.getCheckOutSessionFromDatabaseWithEmployee(employee1)).thenReturn(checkOutSessionsA);
        //Mockito.when(checkOutSessionsA.get(0)).thenReturn(checkOutSession1);
        //Mockito.when(checkOutSessionsA.get(1)).thenReturn(checkOutSession2);
        //Mockito.when(checkOutSessionsA.get(2)).thenReturn(checkOutSession3);
        //Mockito.when(fakeCheckOutSessionDatabase.getCheckOutSessionFromDatabaseWithEmployee(fakeEmployeeDatabase.getEmployee("Anna"))).thenReturn(checkOutSessionsA);
    }

    @Test
    void getAverageSalaryReturnsCorrectNumber() {
        assertEquals(40_000, statistics.getAverageSalary());
    }

    @Test
    void getCustomerMostSold() {
        Product pasta = fakeProductDatabase.getProductFromDatabase("Butter");
        System.out.println(pasta);
        Product p = statistics.getCustomerMostSold(fakeCustomerDatabase.getCustomer("Jacob"));
        assertEquals(pasta, p);
    }

    @Test
    void getEmployeeAverageCheckOutSessionLengthTest() {
        //ArrayList<CheckOutSession> ls = fakeCheckOutSessionDatabase.getCheckOutSessionFromDatabaseWithEmployee(fakeEmployeeDatabase.getEmployee("Anna"));
        assertEquals(16800, statistics.getAverageCheckOutSessionLength(employee1, fakeCheckOutSessionDatabase));
    }

//   @Test
//    void getEmployeesSortedBySpeedTest() {
//        System.out.println(statistics.getEmployeesSortedBySpeed(checkOutSessionsA));
//    }

    @Test
    void PullingTopFiveProductsEverSoldReturnsCorrectResult(){
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
    void GetCustomerWhoShoppedMostReturnsCorrectCustomer(){
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

}