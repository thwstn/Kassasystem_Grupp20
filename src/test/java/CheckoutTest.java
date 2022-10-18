import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.server.Session;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutTest {

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
