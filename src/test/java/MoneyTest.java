import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTest {

    private static final List<Integer> DENOMINATION_LIST = List.of(100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100);
    private static final int INVALID_DENOMINATION = 123;
    private Money moneyWithBalanceZero;
    private Money moneyWithBalanceNonZero;

    @BeforeEach
    void init() {
        this.moneyWithBalanceZero = new Money();

        HashMap<Integer, Integer> nonZeroMoney = new HashMap<>();
        nonZeroMoney.put(100000, 10);
        nonZeroMoney.put(50000, 10);
        nonZeroMoney.put(20000, 10);
        nonZeroMoney.put(10000, 10);
        nonZeroMoney.put(5000, 10);
        nonZeroMoney.put(2000, 10);
        nonZeroMoney.put(1000, 10);
        nonZeroMoney.put(500, 10);
        nonZeroMoney.put(200, 10);
        nonZeroMoney.put(100, 10);
        this.moneyWithBalanceNonZero = new Money(nonZeroMoney);
    }

    @Test
    void emptyMoney() {
        int balance = 0;
        for (Map.Entry<Integer, Integer> denomination : moneyWithBalanceZero.getDenominationAmounts().entrySet()) {
            balance += denomination.getValue();
        }
        assertEquals(0, balance, "Balance should be 0");
    }

    @Test
    void createMoneyWithMoney() {
        HashMap<Integer, Integer> newMoneySetup = new HashMap<>();
        newMoneySetup.put(100000, 1);
        newMoneySetup.put(50000, 1);
        newMoneySetup.put(20000, 1);
        newMoneySetup.put(10000, 1);
        newMoneySetup.put(5000, 1);
        newMoneySetup.put(2000, 1);
        newMoneySetup.put(1000, 1);
        newMoneySetup.put(500, 1);
        newMoneySetup.put(200, 1);
        newMoneySetup.put(100, 1);

        Money createMoneyWithMoneyTest = new Money(newMoneySetup);
        assertEquals(188800, createMoneyWithMoneyTest.checkAmount());
    }

    @ParameterizedTest(name = "{index} The map contains {0}, a valid denomination")
    @CsvSource(value = {"100", "100000", "200", "50000", "500", "20000", "1000", "10000", "2000", "5000"})
    void createMoneyOfValidDenomination(int denomination) {
        HashMap<Integer, Integer> newMoneyMap = new HashMap<>();
        newMoneyMap.put(denomination, 1);
        Money newMoney = new Money(newMoneyMap);
        assertTrue(DENOMINATION_LIST.contains(denomination) && newMoney.getDenominationAmounts().containsKey(denomination));
    }


    @Test
    void createMoneyOfInvalidDenomination() {
        assertThrows(IllegalArgumentException.class, () -> {
            HashMap<Integer, Integer> newMoneyMap = new HashMap<>();
            newMoneyMap.put(INVALID_DENOMINATION, 1);
            new Money(newMoneyMap);
        });
    }

    @Test
    void checkAmountOfMoney() {
        assertEquals(1888000, moneyWithBalanceNonZero.checkAmount());
    }

    @Test
    void addOneUnitOfMoney() {
        Money newMoney = moneyWithBalanceZero.add(1000);
        assertEquals(1000, newMoney.checkAmount());
    }

    @Test
    void addMoneyToMoney() {
        moneyWithBalanceZero.add(1000);
        Money newMoney = moneyWithBalanceNonZero.add(moneyWithBalanceZero);
        assertEquals(1888000 + 1000, newMoney.checkAmount());
    }

    @Test
    void addMoneyOfInvalidDenomination() {
        assertThrows(IllegalArgumentException.class, () -> {
            Money newMoney = moneyWithBalanceZero.add(INVALID_DENOMINATION);
        });
    }

    @Test
    void removeOneUnitOfMoney() {
        Money newMoney = moneyWithBalanceNonZero.remove(1000);
        assertEquals(1888000 - 1000, moneyWithBalanceNonZero.checkAmount());
    }

    @Test
    void removeMoneyThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> {
            Money newMoney = moneyWithBalanceZero.remove(1000);
        });
    }

    @Test
    void removeMoneyFromMoney() {
        Money newMoney = moneyWithBalanceNonZero.remove(moneyWithBalanceNonZero);
        assertEquals(1888000 - 1888000, newMoney.checkAmount());
    }

    @Test
    void addMoneyTwice() {
        Money newMoney = moneyWithBalanceZero.add(1000);
        Money moreNewMoney = newMoney.add(1000);
        assertEquals(2000, moreNewMoney.checkAmount());
    }

}
