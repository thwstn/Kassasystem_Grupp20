import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.server.Session;

import javax.swing.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class CheckoutTest {

    private Money money;

    @BeforeEach
    void init() {
        TreeMap<Integer, Integer> denominations = new TreeMap<>();
        denominations.put(100000, 10);
        denominations.put(50000, 10);
        denominations.put(20000, 10);
        denominations.put(10000, 10);
        denominations.put(5000, 10);
        denominations.put(2000, 10);
        denominations.put(1000, 10);
        denominations.put(500, 10);
        denominations.put(200, 10);
        denominations.put(100, 10);
        money = new Money(denominations);
    }

    @Test
    void getIDReturnsUUID() {
        Checkout checkout = new Checkout();
        assertEquals(true, checkout.getID() instanceof UUID, "Wrong type for ID");
    }

    @Test
    void checkOutSessionIsNullByDefault() {
        Checkout checkout = new Checkout();
        assertEquals(null, checkout.getCheckOutSession(), "CheckOutSession should be null");
    }

    @Test
    void moneyIsEmptyByDefault() {
        Checkout checkout = new Checkout();
        assertEquals(0, checkout.getMoney().checkAmount(), "Money balance should be zero by defualt");
    }

    @Test
    void checkOutHasNoCurrentOrder() {
        Checkout checkout = new Checkout();
        assertEquals(null, checkout.getOrder());
    }

    @Test
    void logInEmployeeAndCreateASession() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        assertTrue(checkout.getCheckOutSession() != null, "Session should not be null");
    }

    @Test
    void logOutEmployeeAndEndSession() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.logoutEmployee();
        assertTrue(checkout.getCheckOutSession() == null);
    }

    @Test
    void changeEmployeeAndStartANewSession() {
        Checkout checkout = new Checkout();
        Employee employee1 = new Employee("Lisa", 30000);
        Employee employee2 = new Employee("Harald", 29000);
        checkout.loginEmployee(employee1);
        checkout.changeEmployee(employee2);
        assertEquals("Harald", checkout.getCheckOutSession().getEmployee().getName(), "Employee not changed, or never set at all");
    }

    @Test
    void createEmptyOrderWhileLoggedIn() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.scanEAN(917563847583L);
        //checkout.addNewEmptyOrder();
        assertTrue(checkout.getOrder() != null);
    }

    //createEmptyOrderWhileLoggedOut
    @Test
    void removeOrder() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.scanEAN(917563847583L);
        //checkout.addNewEmptyOrder();
        checkout.removeOrder();
        assertEquals(null, checkout.getOrder(), "Order exist but i should not");
    }

    @Test
    void scanEANWithNoActiveOrderCreatesNewOrder() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.scanEAN(917563847583L);
        assertTrue(checkout.getOrder() != null);
    }

    @Test
    void payWithCardUpdatesOrderDatabase() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.scanEAN(917563847583L);
        Order order = checkout.getOrder();
        //Order order1 = new Order(employee, new OrderLine("test", 1, 1));
        checkout.payWithCard();
        assertTrue(checkout.orderDatabase.orderExistsInDatabase(order));
    }

    @Test
    void addMoneyAddsMoney() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.addMoney(money);
        assertEquals(10, checkout.getMoney().checkDenominationAmount(100000), "Money is not aded to checkout");
    }

    @Test
    void payWithCashUpdatesMoney() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.addMoney(money);
        checkout.scanEAN(917563848693L);

        Money moneyFromCustomer = new Money();
        moneyFromCustomer = moneyFromCustomer.add(50000);
        checkout.payWithCash(moneyFromCustomer);
        assertEquals(1888000 + 5700, checkout.getMoney().checkAmount());
    }

    //PaywithCashGivesCorrectDenominationsInChange
    @Test
    void payWithCashGivesCorrectDenominationsInChange() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.addMoney(money);
        checkout.scanEAN(9684736485769L);
        checkout.scanEAN(9684736485769L);
        checkout.scanEAN(961063847583L);

        Money moneyFromCustomer = new Money();
        moneyFromCustomer = moneyFromCustomer.add(100000);
        moneyFromCustomer = moneyFromCustomer.add(50000);
        moneyFromCustomer = moneyFromCustomer.add(100);

        checkout.payWithCash(moneyFromCustomer);

        boolean correctMoney = true;
        if (checkout.getMoney().checkDenominationAmount(100000) != 11) {
            correctMoney = false;
        } else if (checkout.getMoney().checkDenominationAmount(50000) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().checkDenominationAmount(20000) != 8) {
            correctMoney = false;
        } else if (checkout.getMoney().checkDenominationAmount(10000) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().checkDenominationAmount(5000) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().checkDenominationAmount(2000) != 8) {
            correctMoney = false;
        } else if (checkout.getMoney().checkDenominationAmount(1000) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().checkDenominationAmount(500) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().checkDenominationAmount(200) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().checkDenominationAmount(100) != 11) {
            correctMoney = false;
        }
        assertTrue(correctMoney, "not good");
    }

    @Test
    void PayWithCashAndChangeRequiredNotAvailableCancelsPurchaseAndReturnsMoney() {
        Checkout c = new Checkout();
        c.loginEmployee(new Employee("Lisa", 30000));
        TreeMap<Integer, Integer> moneyMap = new TreeMap<>();
        moneyMap.put(50000, 10);
        Money newMoney = new Money(moneyMap);
        c.addMoney(newMoney);
        c.scanEAN(917263847583L);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1000, 1);
        Assertions.assertThrows(IllegalStateException.class, () -> c.payWithCash((new Money(map))));
    }

    @Test
    void PayWithCashAndNotEnoughMoneyThrowsException() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.addMoney(money);
        checkout.scanEAN(917563848693L);
        Money moneyPayment = new Money();
        moneyPayment.add(500);

        assertThrows(IllegalArgumentException.class, () ->
                checkout.payWithCash(moneyPayment));
    }

    @Test
    void customerPaysCashWithZeroMoney() {
        Checkout checkout = new Checkout();
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.addMoney(money);
        checkout.scanEAN(917563848693L);

        assertThrows(IllegalArgumentException.class, () ->
                checkout.payWithCash(new Money()));
    }

    @Test
    void ParkingOrderCorrectlyPlacesItInParkingList() {
        Checkout c = new Checkout();
        c.loginEmployee(new Employee("Lisa", 30000));
        c.scanEAN(917563848693L);
        Customer customer = new Customer("Pelle", 12);
        c.addCustomerToOrder(customer);
        c.scanEAN(917563848693L);
        c.scanEAN(917563849363L);
        Order tempOrder = c.getOrder();
        c.parkOrder();
        Assertions.assertEquals(tempOrder, c.getParkedOrder("Pelle"));
    }

    @Test
    void UnparkingOrderCorrectlyPlacesTheParkedOrderAsTheActiveOrder() {
        Checkout c = new Checkout();
        c.loginEmployee(new Employee("Lisa", 30000));
        c.scanEAN(917563848693L);
        Customer customer = new Customer("Pelle", 12);
        c.addCustomerToOrder(customer);
        c.scanEAN(917563848693L);
        c.scanEAN(917563849363L);
        Order tempOrder = c.getOrder();
        c.parkOrder();
        c.unparkOrder("Pelle");
        Assertions.assertEquals(tempOrder, c.getOrder());
        Assertions.assertNull(c.getParkedOrder("Pelle"));
    }

    @Test
    void TestingTwoPurchasesGoesThrough(){
        Checkout checkout = new Checkout();
        Customer customer = new Customer("Olof", 97);
        checkout.addMoney(money);
        Employee employee = checkout.fakeEmployeeDatabase.getEmployee("Anna");

        //A -> B Båge 1
        checkout.loginEmployee(employee);

        //B -> C Båge 3
        checkout.scanEAN(961063847583L);

        //C -> C Båge 6
        checkout.scanEAN(917563840003L);

        //C -> B Båge 4
        checkout.addCustomerToOrder(customer);
        checkout.parkOrder();

        //B -> C Båge 3 & 6
        checkout.scanEAN(917563847583L);
        checkout.scanEAN(9485736253926L);
        checkout.scanEAN(928374658273L);
        checkout.scanEAN(8573928374659L);
        checkout.scanEAN(8573928374659L);
        checkout.scanEAN(8573928374659L);
        checkout.scanEAN(917563927583L);
        checkout.scanEAN(917563849363L);

        // C -> D -> E -> B Båge 7 & 8 & 10
        Money customerMoney = new Money();
        customerMoney = customerMoney.add(50000);
        String receipt1 = checkout.payWithCash(customerMoney);

        // B -> A Båge 2
        checkout.logoutEmployee();

        // A -> B Båge 1
        Employee employee2 = (checkout.fakeEmployeeDatabase.getEmployee("Daniella"));
        checkout.loginEmployee(employee2);

        // B -> C Båge 5
        checkout.unparkOrder("Olof");

        // C -> E -> B Båge 9 & 10
        String receipt2 = checkout.payWithCard();

        // B -> A Båge 2
        checkout.logoutEmployee();

        int balance = checkout.getMoney().checkAmount();
        Assertions.assertEquals(1896000, balance); //Money in checkout is correct
        Assertions.assertEquals(9, checkout.orderDatabase.getAllOrders().size()); //Ordrarna har lagts till i databasen
    }
}

/*public class CheckoutTest {

    @Test
    void numberOfOrderIdsTest() {
        ArrayList<Integer> orderIds = new ArrayList<>();
        orderIds.add(12);
        orderIds.add(34);
        orderIds.add(56);
        orderIds.add(78);
        orderIds.add(90);
        Checkout checkout = new Checkout(123, 456, orderIds, 789);

        assertEquals(5, checkout.getOrderIds().size(), "Should have been 5 order IDs");
    }

    @Test
    void getCheckoutID() {
        Checkout checkout = getEmptyCheckout();
        assertEquals(123, checkout.getId(), "Wrong ID of CheckOut");
    }

    @Test
    void getMoneyID() {
        Checkout checkout = getEmptyCheckout();
        assertEquals(789, checkout.getMoneyId(), "Wrong moneyID");
    }

    @Test
    void loginEmployeeTest() {
        Checkout checkout = getEmptyCheckout();
        checkout.loginEmployee(999);
        assertEquals(999, checkout.getSession().getEmployeeId(), "Wrong or no employee logged in");
    }
    @Test
    void employeeIsLoggedIn() {
        Checkout checkout = getEmptyCheckout();
        checkout.loginEmployee(999);
        assertEquals(true, checkout.employeeIsLoggedInToCheckout(), "Employee should be logged in");
    }

    @Test
    void changeEmployee() {
        Checkout checkout = getEmptyCheckout();
        checkout.loginEmployee(999);
        checkout.changeEmployee(888);
        assertEquals(888, checkout.getSession().getEmployeeId(), "Wrong employee logged in");
    }

    @Test
    void noEmployeeLoggedIn() {
        Checkout checkout = getEmptyCheckout();
        checkout.loginEmployee(111);
        checkout.logoutEmployee();
        assertEquals(null, checkout.getSession(), "There is still an active session");
    }

    @Test
    void logOutEmployee() {
        Checkout checkout = getEmptyCheckout();
        checkout.loginEmployee(111);
        checkout.logoutEmployee();
        assertThrows(NullPointerException.class, () -> {
            checkout.getSession().getEmployeeId();
        });
    }

    @Test
    void checkOutSessionHistoryTest() {
        Checkout checkout = getEmptyCheckout();
        for (int i = 100; i <= 500; i += 100) {
            checkout.loginEmployee(i);
            checkout.logoutEmployee();
        }
        boolean controlBolean = true;
        for (CheckOutSession checkOutSession : checkout.getCheckOutSessionsHistory()) {
            if (checkOutSession.getStartDate() == null || checkOutSession.getEmployeeId() < 0 || checkOutSession.getEndDate() == null) {
                controlBolean = false;
            }
        }
        assertEquals(true, controlBolean, "Something went wrong in checkoutHistory");
    }

    @Test
    void returnAllUniqueEmployeesFromSessionHistory() {
        Checkout checkout = getEmptyCheckout();
        HashSet<Integer> employeeIDs = new HashSet<>();
        for (int i = 100; i <= 500; i = i+100) {
            checkout.loginEmployee(i);
            checkout.logoutEmployee();
            employeeIDs.add(i);
        }
        for (int i = 100; i <= 300; i = i+50) {
            checkout.loginEmployee(i);
            checkout.logoutEmployee();
            employeeIDs.add(i);
        }
        assertEquals(true, checkout.getUniqueEmployeeIDsFromSessionHistory().equals(employeeIDs), "Wrong set returned!");
    }

    @Test
    void GetProductFromDataBaseReturnsCorrectProduct(){
        FakeProductDatabase d1 = new FakeProductDatabase();
        d1.fillDatabase();
        Product p1 = d1.getProductFromDatabase(new EAN(917563927583L));
        assertEquals("Pasta",p1.getName());
    }

    private Checkout getEmptyCheckout() {
        ArrayList<Integer> orderIds = new ArrayList<>();
        Checkout checkout = new Checkout(123, 456, orderIds, 789);
        return  checkout;
    }
}
*/