import java.util.*;

public class Money {

    private final TreeMap<Integer, Integer> denominationAmounts;
    private static final List<Integer> DENOMINATIONS = List.of(
            1000_00, 500_00, 200_00, 100_00, 50_00, 20_00, 10_00, 5_00, 2_00, 1_00);
    private static final int LOWEST_BILL_VALUE = 20_00;
    private static final int HIGHEST_COIN_VALUE = 10_00;
    private static final int DECIMAL_TO_INTEGER_DIVISOR = 100;

    public Money() {
        this.denominationAmounts = new TreeMap<>(createEmptyMoneyMap());
    }

    public Money(TreeMap<Integer, Integer> denominations) {
        if (denominations == null) {
            throw new IllegalArgumentException("Cannot create money with null");
        }
        for (int denomination : denominations.keySet()) {
            if (denominations.get(denomination) < 0) {
                throw new IllegalArgumentException("Cannot have negative balance of any denomination");
            }
            if (!DENOMINATIONS.contains(denomination)) {
                throw new IllegalArgumentException("Not a valid denomination");
            }
        }
        denominationAmounts = createCopyOfMoneyMap(denominations);
    }

    private TreeMap<Integer, Integer> createEmptyMoneyMap() {
        TreeMap<Integer, Integer> denominations = new TreeMap<>();
        for (int denomination : DENOMINATIONS) {
            denominations.put(denomination, 0);
        }
        return denominations;
    }

    private TreeMap<Integer, Integer> createCopyOfMoneyMap(Map<Integer, Integer> oldMap) {
        TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>();
        for (int denomination : DENOMINATIONS) {
            newMoneyMap.put(denomination, oldMap.getOrDefault(denomination, 0));
        }
        return newMoneyMap;
    }

    public TreeMap<Integer, Integer> getDenominationAmounts() {
        return createCopyOfMoneyMap((denominationAmounts));
    }

    public int getSpecificDenominationAmounts(int denomination) {
        return denominationAmounts.get(denomination);
    }

    public int getBalance() {
        int balance = 0;
        for (int denomination : denominationAmounts.keySet()) {
            int tempBalance = denominationAmounts.get(denomination) * denomination;
            balance += tempBalance;
        }
        return balance;
    }

    /**
     * Returns a new Money object with the same values as the current one plus
     * one of the denomination in the parameter.
     *
     * @param denomination Has to be an int from the list of valid denominations.
     * @return New Money object, has to be instantiated to use further.
     */
    public Money add(int denomination) {
        if (!DENOMINATIONS.contains(denomination)) {
            throw new IllegalArgumentException("Not a valid denomination");
        }
        TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>(this.getDenominationAmounts());

        newMoneyMap.put(denomination, newMoneyMap.get(denomination) + 1);
        return new Money(newMoneyMap);
    }

    /**
     * Adds two Money objects together and returns a fresh one.
     *
     * @param incomingMoney Money object to be added to existing object.
     * @return New Money object, has to be instantiated to use further.
     */
    public Money add(Money incomingMoney) {
        TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>();

        for (int denomination : incomingMoney.getDenominationAmounts().keySet()) {
            int amount;
            amount = this.denominationAmounts.get(denomination) + incomingMoney.getDenominationAmounts().get(denomination);
            newMoneyMap.put(denomination, amount);
        }
        return new Money(newMoneyMap);
    }

    public Money remove(int denomination) {
        if (!DENOMINATIONS.contains(denomination)) {
            throw new IllegalArgumentException("Not a valid denomination");
        }
        if ((denominationAmounts.get(denomination) < 1)) {
            throw new IllegalArgumentException("There are no units of that denomination (" + denomination + ")");
        }
        TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>(this.getDenominationAmounts());

        newMoneyMap.put(denomination, newMoneyMap.get(denomination) - 1);
        return new Money(newMoneyMap);
    }

    public Money remove(Money incomingMoney) {
        if (incomingMoney.getBalance() > this.getBalance()) {
            throw new IllegalArgumentException("Not enough money in balance");
        }
        TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>();

        for (int denomination : incomingMoney.getDenominationAmounts().keySet()) {
            int amount;
            amount = this.denominationAmounts.get(denomination) - incomingMoney.getDenominationAmounts().get(denomination);
            newMoneyMap.put(denomination, amount);
        }
        return new Money(newMoneyMap);
    }

    public Money giveChange(double changeToGet) {
        Money newMoneyToReturn = new Money();
        TreeMap<Integer, Integer> moneyLeft = new TreeMap<>(denominationAmounts);
        double remainingChangeToGet = changeToGet;
        for (int key : moneyLeft.descendingKeySet()) {
            while (remainingChangeToGet >= key) {
                if (moneyLeft.get(key) > 0) {
                    moneyLeft.put(key, moneyLeft.get(key) - 1);
                    newMoneyToReturn = newMoneyToReturn.add(key);
                    remainingChangeToGet -= key;
                } else {
                    break;
                }
            }
        }
        if (remainingChangeToGet > 0) {
            return null;
        }
        return newMoneyToReturn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int denomination : DENOMINATIONS) {
            if (denomination >= LOWEST_BILL_VALUE) {
                sb.append(denomination / DECIMAL_TO_INTEGER_DIVISOR).append("(sedlar): ").append
                        (getSpecificDenominationAmounts(denomination)).append("\n");
            }
            if (denomination <= HIGHEST_COIN_VALUE) {
                sb.append(denomination / DECIMAL_TO_INTEGER_DIVISOR).append("(mynt): ").append
                        (getSpecificDenominationAmounts(denomination)).append("\n");
            }
        }
        sb.append("Totalt: ").append(getBalance() / DECIMAL_TO_INTEGER_DIVISOR).append("kr");
        return sb.toString();
    }
}
