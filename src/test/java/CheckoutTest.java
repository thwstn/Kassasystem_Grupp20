import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class CheckoutTest {

    private Money money;
    private Checkout checkout;

    private final FakeOrderDatabase fakeOrderDatabase = new FakeOrderDatabase();
    private final FakeEmployeeDatabase fakeEmployeeDatabase = new FakeEmployeeDatabase();
    private final FakeCustomerDatabase fakeCustomerDatabase = new FakeCustomerDatabase();
    private final FakeProductDatabase fakeProductDatabase = new FakeProductDatabase();
    private final FakeCheckOutSessionDatabase fakeCheckOutSessionDatabase = new FakeCheckOutSessionDatabase();


    @BeforeEach
    void init() {
        checkout = new Checkout(fakeCheckOutSessionDatabase, fakeProductDatabase, fakeOrderDatabase, fakeEmployeeDatabase, fakeCustomerDatabase);
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
        assertEquals(true, checkout.getID() instanceof UUID, "Wrong type for ID");
    }

    @Test
    void checkOutSessionIsNullByDefault() {
        assertEquals(null, checkout.getCheckOutSession(), "CheckOutSession should be null");
    }

    @Test
    void moneyIsEmptyByDefault() {
        assertEquals(0, checkout.getMoney().getBalance(), "Money balance should be zero by defualt");
    }

    @Test
    void checkOutHasNoCurrentOrder() {
        assertEquals(null, checkout.getOrder());
    }

    @Test
    void logInEmployeeAndCreateASession() {
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        assertTrue(checkout.getCheckOutSession() != null, "Session should not be null");
    }

    @Test
    void logOutEmployeeAndEndSession() {
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.logoutEmployee();
        assertTrue(checkout.getCheckOutSession() == null);
    }

    @Test
    void changeEmployeeAndStartANewSession() {
        Employee employee1 = new Employee("Lisa", 30000);
        Employee employee2 = new Employee("Harald", 29000);
        checkout.loginEmployee(employee1);
        checkout.changeEmployee(employee2);
        assertEquals("Harald", checkout.getCheckOutSession().getEmployee().getName(), "Employee not changed, or never set at all");
    }

    @Test
    void createEmptyOrderWhileLoggedIn() {
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.scanEAN(917563847583L);
        //checkout.addNewEmptyOrder();
        assertTrue(checkout.getOrder() != null);
    }

    //createEmptyOrderWhileLoggedOut
    @Test
    void removeOrder() {
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.scanEAN(917563847583L);
        //checkout.addNewEmptyOrder();
        checkout.removeOrder();
        assertEquals(null, checkout.getOrder(), "Order exist but i should not");
    }

    @Test
    void payWithCardUpdatesOrderDatabase() {
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
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.addMoney(money);
        assertEquals(10, checkout.getMoney().getSpecificDenominationAmounts(100000), "Money is not aded to checkout");
    }

    @Test
    void payWithCashUpdatesMoney() {
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.addMoney(money);
        checkout.scanEAN(917563848693L);

        Money moneyFromCustomer = new Money();
        moneyFromCustomer = moneyFromCustomer.add(50000);
        checkout.payWithCash(moneyFromCustomer);
        assertEquals(1888000 + 5700, checkout.getMoney().getBalance());
    }

    //PaywithCashGivesCorrectDenominationsInChange
    @Test
    void payWithCashGivesCorrectDenominationsInChange() {
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
        if (checkout.getMoney().getSpecificDenominationAmounts(100000) != 11) {
            correctMoney = false;
        } else if (checkout.getMoney().getSpecificDenominationAmounts(50000) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().getSpecificDenominationAmounts(20000) != 8) {
            correctMoney = false;
        } else if (checkout.getMoney().getSpecificDenominationAmounts(10000) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().getSpecificDenominationAmounts(5000) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().getSpecificDenominationAmounts(2000) != 8) {
            correctMoney = false;
        } else if (checkout.getMoney().getSpecificDenominationAmounts(1000) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().getSpecificDenominationAmounts(500) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().getSpecificDenominationAmounts(200) != 10) {
            correctMoney = false;
        } else if (checkout.getMoney().getSpecificDenominationAmounts(100) != 11) {
            correctMoney = false;
        }
        assertTrue(correctMoney, "not good");
    }

    @Test
    void PayWithCashAndChangeRequiredNotAvailableCancelsPurchaseAndReturnsMoney() {

        checkout.loginEmployee(new Employee("Lisa", 30000));
        TreeMap<Integer, Integer> moneyMap = new TreeMap<>();
        moneyMap.put(50000, 10);
        Money newMoney = new Money(moneyMap);
        checkout.addMoney(newMoney);
        checkout.scanEAN(917263847583L);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1000, 1);
        Assertions.assertThrows(IllegalStateException.class, () -> checkout.payWithCash((new Money(map))));
    }

    @Test
    void PayWithCashAndNotEnoughMoneyThrowsException() {
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
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.addMoney(money);
        checkout.scanEAN(917563848693L);

        assertThrows(IllegalArgumentException.class, () ->
                checkout.payWithCash(new Money()));
    }

    @Test
    void ParkingOrderCorrectlyPlacesItInParkingList() {
        checkout.loginEmployee(new Employee("Lisa", 30000));
        checkout.scanEAN(917563848693L);
        Customer customer = new Customer("Pelle", 12);
        checkout.addCustomerToOrder(customer);
        checkout.scanEAN(917563848693L);
        checkout.scanEAN(917563849363L);
        Order tempOrder = checkout.getOrder();
        checkout.parkOrder();
        Assertions.assertEquals(tempOrder, checkout.getParkedOrder("Pelle"));
    }

    @Test
    void UnparkingOrderCorrectlyPlacesTheParkedOrderAsTheActiveOrder() {
        checkout.loginEmployee(new Employee("Lisa", 30000));
        checkout.scanEAN(917563848693L);
        Customer customer = new Customer("Pelle", 12);
        checkout.addCustomerToOrder(customer);
        checkout.scanEAN(917563848693L);
        checkout.scanEAN(917563849363L);
        Order tempOrder = checkout.getOrder();
        checkout.parkOrder();
        checkout.unparkOrder("Pelle");
        Assertions.assertEquals(tempOrder, checkout.getOrder());
        Assertions.assertNull(checkout.getParkedOrder("Pelle"));
    }

    @Test
    void TestingTwoPurchasesGoesThrough(){
        Customer customer = new Customer("Olof", 97);
        checkout.addMoney(money);
        Employee employee = fakeEmployeeDatabase.getEmployee("Anna");

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
        Employee employee2 = (fakeEmployeeDatabase.getEmployee("Daniella"));
        checkout.loginEmployee(employee2);

        // B -> C Båge 5
        checkout.unparkOrder("Olof");

        // C -> E -> B Båge 9 & 10
        String receipt2 = checkout.payWithCard();

        // B -> A Båge 2
        checkout.logoutEmployee();

        int balance = checkout.getMoney().getBalance();
        Assertions.assertEquals(1896000, balance); //Money in checkout is correct
        Assertions.assertEquals(9, checkout.orderDatabase.getAllOrders().size()); //Ordrarna har lagts till i databasen
    }

    @Test //test 1
    void databaseIsNullTest() {
        ProductDatabase nullDatabase = null;
        Checkout checkout2 = new Checkout(fakeCheckOutSessionDatabase,nullDatabase,fakeOrderDatabase, fakeEmployeeDatabase, fakeCustomerDatabase);
        checkout2.loginEmployee(new Employee("Lisa", 30000));
        assertThrows(NullPointerException.class, ()-> checkout2.scanEAN(917547847583L));
    }

    @Test //test 2
    void eanIsNullTest() {
        checkout.loginEmployee(new Employee("Lisa", 30000));
        Long longThatIsNull = null;
        assertThrows(NullPointerException.class, ()-> checkout.scanEAN(longThatIsNull));
    }

    @Test //test 3
    void tooShortEan() {
        checkout.loginEmployee(new Employee("Lisa", 30000));
        assertThrows(IllegalArgumentException.class, ()-> checkout.scanEAN(91754784758L));
    }

    @Test //test 4
    void tooLongEan() {
        checkout.loginEmployee(new Employee("Lisa", 30000));
        assertThrows(IllegalArgumentException.class, ()-> checkout.scanEAN(91754784758311L));
    }

    @Test //test 5
    void notALongTest() {
        checkout.loginEmployee(new Employee("Lisa", 30000));
        assertThrows(NumberFormatException.class, ()-> checkout.scanEAN(Long.parseLong("NOT_A_LONG")));
    }

    @Test //test 6
    void eanIsNegative() {
        checkout.loginEmployee(new Employee("Lisa", 30000));
        assertThrows(IllegalArgumentException.class, ()-> checkout.scanEAN(-917547847583L));
    }

    @Test //test 7
    void dataBaseDoNotContainProduct() {
        checkout.loginEmployee(new Employee("Lisa", 30000));
        assertThrows(NullPointerException.class, ()-> checkout.scanEAN(117547847583L));
    }

    @Test //test 8
    void scanEANWithNoActiveOrderCreatesNewOrder() {
        Employee employee = new Employee("Lisa", 30000);
        checkout.loginEmployee(employee);
        checkout.scanEAN(8573928374659L);
        assertTrue(checkout.getOrder() != null);
    }

    @Test //test 9
    void orderIsNotNullAndAddsProduct() {
        checkout.loginEmployee(new Employee("Lisa", 30000));
        checkout.scanEAN(917563847583L);
        checkout.scanEAN(961063847583L);
        //Collection<OrderLine> orderLineCollection = checkout.getOrder().getOrderLineList();
        assertEquals("1", checkout.getOrder().toString());
    }
}