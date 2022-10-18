import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {

    FakeEmployeeDatabase fakeEmployeeDatabase;
    Statistics statistics;
    FakeOrderDatabase fakeOrderDatabase;

    @BeforeEach
        void init() {
            fakeEmployeeDatabase = new FakeEmployeeDatabase();
            statistics = new Statistics();
            fakeEmployeeDatabase.fillDatabase();
    }

    @Test
    void getAverageSalaryReturnsCorrectNumber() {
        assertEquals(150, statistics.getAverageSalary());
    }
}