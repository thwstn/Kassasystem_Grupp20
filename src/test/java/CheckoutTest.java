import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

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
    /*@Test
    void logoutEmployeeTest() {
        Checkout checkout = getEmptyCheckout();
        checkout.loginEmployee(888);
        checkout.logoutEmployee();
        assertEquals(null, checkout.getSession(), "Employee is not logged out from checkout");
    }*/
    /*void changeEmployeeTest() {
        Checkout checkout = getEmptyCheckout();

        checkout.changeEmployee(654);
        assertEquals(654, checkout.getSession().getEmployeeId());
    }*/

    @Test
    void logOutEmployee() {
        Checkout checkout = getEmptyCheckout();
    }

    private Checkout getEmptyCheckout() {
        ArrayList<Integer> orderIds = new ArrayList<>();
        Checkout checkout = new Checkout(123, 456, orderIds, 789);
        return  checkout;
    }
}
