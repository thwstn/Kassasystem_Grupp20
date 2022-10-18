import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {

    FakeEmployeeDatabase fakeEmployeeDatabase;
    Statistics statistics;
    FakeOrderDatabase fakeOrderDatabase;
    FakeCustomerDatabase fakeCustomerDatabase;
    FakeProductDatabase fakeProductDatabase;

    @BeforeEach
        void init() {
            fakeEmployeeDatabase = new FakeEmployeeDatabase();
            statistics = new Statistics();
            fakeCustomerDatabase = new FakeCustomerDatabase();
            fakeProductDatabase = new FakeProductDatabase();
    }

    @Test
    void getAverageSalaryReturnsCorrectNumber() {
        assertEquals(150, statistics.getAverageSalary());
    }

    @Test
    void getCustomerMostSold() {
        Product pasta = fakeProductDatabase.getProductFromName("Butter");
        System.out.println(pasta);
        Product p = statistics.getCustomerMostSold(fakeCustomerDatabase.getCustomer("Jacob"));
        assertEquals(pasta, p);
    }
}