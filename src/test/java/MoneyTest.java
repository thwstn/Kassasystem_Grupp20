import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MoneyTest {

    private static final List<Integer> DENOMINATIONS = List.of(
            1000_00, 500_00, 200_00, 100_00, 50_00, 20_00, 10_00, 5_00, 2_00, 1_00);
    private static final int INVALID_DENOMINATION = 1;
    private static final int NON_ZERO_MONEY_BALANCE_AMOUNT = 1888000;
    private Money moneyWithBalanceZero;
    private Money moneyWithBalanceNonZero;

    @BeforeEach
    void init() {
        this.moneyWithBalanceZero = new Money();
        TreeMap<Integer, Integer> denominations = new TreeMap<>();
        for (int denomination : DENOMINATIONS) {
            denominations.put(denomination, 10);
        }
        this.moneyWithBalanceNonZero = new Money(denominations);
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
        TreeMap<Integer, Integer> newMoneySetup = new TreeMap<>();
        for (int denomination : DENOMINATIONS) {
            newMoneySetup.put(denomination, 1);
        }
        Money createMoneyWithMoneyTest = new Money(newMoneySetup);
        assertEquals(188800, createMoneyWithMoneyTest.getBalance());
    }

    @ParameterizedTest(name = "{index} The map contains {0}, a valid denomination")
    @CsvSource(value = {"100", "100000", "200", "50000", "500", "20000", "1000", "10000", "2000", "5000"})
    void createMoneyOfValidDenomination(int denomination) {
        TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>();
        newMoneyMap.put(denomination, 1);
        Money newMoney = new Money(newMoneyMap);
        assertTrue(DENOMINATIONS.contains(denomination) && newMoney.getDenominationAmounts().containsKey(denomination));
    }


    @Test
    void createMoneyOfInvalidDenomination() {
        assertThrows(IllegalArgumentException.class, () -> {
            TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>();
            newMoneyMap.put(INVALID_DENOMINATION, 1);
            new Money(newMoneyMap);
        });
    }

    @Test
    void checkAmountOfMoney() {
        assertEquals(NON_ZERO_MONEY_BALANCE_AMOUNT, moneyWithBalanceNonZero.getBalance());
    }

    @Test
    void addOneUnitOfMoney() {
        Money newMoney = moneyWithBalanceZero.add(1000);
        assertEquals(1000, newMoney.getBalance());
    }

    @Test
    void addMoneyToMoney() {
        Money newMoney = moneyWithBalanceZero.add(1000);
        Money moreNewMoney = moneyWithBalanceNonZero.add(newMoney);
        assertEquals(NON_ZERO_MONEY_BALANCE_AMOUNT + 1000, moreNewMoney.getBalance());
    }

    @Test
    void addMoneyOfInvalidDenomination() {
        assertThrows(IllegalArgumentException.class, () ->
                moneyWithBalanceZero.add(INVALID_DENOMINATION));
    }

    @Test
    void removeOneUnitOfMoney() {
        Money newMoney = moneyWithBalanceNonZero.remove(1000);
        assertEquals(NON_ZERO_MONEY_BALANCE_AMOUNT - 1000, newMoney.getBalance());
    }

    @Test
    void removeMoneyThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () ->
                moneyWithBalanceZero.remove(1000));
    }

    @Test
    void removeMoneyFromMoney() {
        Money newMoney = moneyWithBalanceNonZero.remove(moneyWithBalanceNonZero);
        assertEquals(0, newMoney.getBalance());
    }

    @Test
    void removeMoneyOfInvalidDenomination() {
        assertThrows(IllegalArgumentException.class, () ->
                moneyWithBalanceNonZero.remove(INVALID_DENOMINATION));
    }

    @Test
    void addMoneyTwice() {
        Money newMoney = moneyWithBalanceZero.add(1000);
        Money moreNewMoney = newMoney.add(10000);
        assertEquals(11000, moreNewMoney.getBalance());
    }

    @Test
    void addMoneyInMultipleWaysReturnsCorrectBalance() {
        Money newMoney = moneyWithBalanceZero.add(10000);
        Money singleAddNewMoney = newMoney.add(10000);
        Money newMoneyAddedWithMoney = singleAddNewMoney.add(newMoney);
        Money singleAddMoney = newMoneyAddedWithMoney.add(10000);
        assertEquals(40000, singleAddMoney.getBalance());
    }

    @Test
    void checkSpecificDenomination() {
        assertEquals(10, moneyWithBalanceNonZero.getSpecificDenominationAmounts(100));
    }

    @Test
    void removeMoreMoneyThanExists() {
        assertThrows(IllegalArgumentException.class, () ->
                moneyWithBalanceZero.remove(moneyWithBalanceNonZero));
    }

    @Test
    void createNegativeDenominationAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>();
            newMoneyMap.put(100, -1);
            new Money(newMoneyMap);
        });
    }

    @Test
    void constructorFillsEmptyDenominations() {
        TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>();
        newMoneyMap.put(100, 1);
        Money newMoney = new Money(newMoneyMap);
        assertEquals(0, newMoney.getSpecificDenominationAmounts(200));
    }

    @Test
    void constructorDoesNotAcceptNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Money(null));
    }

    @Test
    void toStringTest() {
        assertEquals("""
                1000(sedlar): 10
                500(sedlar): 10
                200(sedlar): 10
                100(sedlar): 10
                50(sedlar): 10
                20(sedlar): 10
                10(mynt): 10
                5(mynt): 10
                2(mynt): 10
                1(mynt): 10
                Totalt: 18880kr""", moneyWithBalanceNonZero.toString());
    }
}
