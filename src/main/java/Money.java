import java.util.*;

public class Money {

    private final TreeMap<Integer, Integer> denominationAmounts;
    private static final List<Integer> DENOMINATION_LIST = List.of(
            100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100);

    public Money() {
        this.denominationAmounts = new TreeMap<>(createEmptyMoneyMap());
    }

    public Money(TreeMap<Integer, Integer> denominations) {
        for (int denomination : denominations.keySet()) {
            if (denominations.get(denomination) < 0) {
                throw new IllegalArgumentException("Cannot have negative balance of any denomination");
            }
            if (!DENOMINATION_LIST.contains(denomination)) {
                throw new IllegalArgumentException("Not a valid denomination");
            }
        }
        for (int denomination : DENOMINATION_LIST) {
            if (!denominations.containsKey(denomination)) {
                denominations.put(denomination, 0);
            }
        }
        this.denominationAmounts = denominations;
    }

    private TreeMap<Integer, Integer> createEmptyMoneyMap() {
        TreeMap<Integer, Integer> denominations = new TreeMap<>();
        denominations.put(100000, 0);
        denominations.put(50000, 0);
        denominations.put(20000, 0);
        denominations.put(10000, 0);
        denominations.put(5000, 0);
        denominations.put(2000, 0);
        denominations.put(1000, 0);
        denominations.put(500, 0);
        denominations.put(200, 0);
        denominations.put(100, 0);
        return denominations;
    }

    public TreeMap<Integer, Integer> getDenominationAmounts() {
        return createNewFilledMoneyMap((denominationAmounts));
    }

    public int checkAmount() {
        int balance = 0;
        for (int denomination : denominationAmounts.keySet()) {
            int tempBalance = denominationAmounts.get(denomination) * denomination;
            balance += tempBalance;
        }
        return balance;
    }

    public Money add(int denomination) {
        if (!DENOMINATION_LIST.contains(denomination)) {
            throw new IllegalArgumentException("Not a valid denomination");
        }
        TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>(this.getDenominationAmounts());

        newMoneyMap.put(denomination, newMoneyMap.get(denomination) + 1);
        return new Money(newMoneyMap);
    }

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
        if (!DENOMINATION_LIST.contains(denomination)) {
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
        if (incomingMoney.checkAmount() > this.checkAmount()) {
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

    private TreeMap<Integer, Integer> createNewFilledMoneyMap(Map<Integer, Integer> oldMap) {
        TreeMap<Integer, Integer> newMoneyMap = new TreeMap<>();
        for (int denomination : oldMap.keySet()) {
            newMoneyMap.put(denomination, oldMap.get(denomination));
        }
        return newMoneyMap;
    }

    public int checkDenominationAmount(int denomination) {
        return denominationAmounts.get(denomination);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int denomination : DENOMINATION_LIST) {
            if (denomination >= 2000) {
                sb.append(denomination / 100).append("(sedlar): ").append(checkDenominationAmount(denomination)).append("\n");
            }
            if (denomination <= 1000) {
                sb.append(denomination / 100).append("(mynt): ").append(checkDenominationAmount(denomination)).append("\n");
            }
        }
        sb.append("Total amount: ").append(checkAmount());
        return sb.toString();
    }
}
