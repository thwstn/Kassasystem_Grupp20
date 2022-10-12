import org.junit.jupiter.api.Test;

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

        assertEquals(5, checkout.orderIds.size(), "Det skulle varit 5 orderIDs");
    }

    @Test
    void changeEmployeeTest() {
        Checkout checkout = getEmptyCheckout();

        checkout.changeEmployee(654);
        assertEquals(654, checkout.getSession().getEmployeeId());
    }

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
