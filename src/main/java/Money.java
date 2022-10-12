import java.util.*;

public class Money {
    private final HashMap<Integer, Integer> denominationAmounts;
    private static final List<Integer> DENOMINATION_LIST = List.of(
            100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100);

    public Money() {
        HashMap<Integer, Integer> denominations = new HashMap<>();
        denominations.put(100000,0);
        denominations.put(50000,0);
        denominations.put(20000,0);
        denominations.put(10000,0);
        denominations.put(5000,0);
        denominations.put(2000,0);
        denominations.put(1000,0);
        denominations.put(500,0);
        denominations.put(200,0);
        denominations.put(100,0);
        this.denominationAmounts = denominations;
    }

    public Money(HashMap<Integer, Integer> denominations) {
        for (int denomination : denominations.keySet()){
            if(!DENOMINATION_LIST.contains(denomination)) {
                throw new IllegalArgumentException("Not a valid denomination");
            }
        }
        this.denominationAmounts = denominations;
    }

    public HashMap<Integer, Integer> getDenominationAmounts() {
        //Går inte att ändra
        return new HashMap<>(Map.copyOf(denominationAmounts));
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
        if(!DENOMINATION_LIST.contains(denomination)) {
            throw new IllegalArgumentException("Not a valid denomination");
        }
        denominationAmounts.put(denomination, denominationAmounts.get(denomination) + 1);

        return new Money(this.getDenominationAmounts());
    }

    public Money add(Money oldMoney) {
        HashMap<Integer, Integer> newMoneyMap = new HashMap<>();

        for (int denomination : oldMoney.getDenominationAmounts().keySet()) {
            int amount;
            amount = this.denominationAmounts.get(denomination) + oldMoney.getDenominationAmounts().get(denomination);
            newMoneyMap.put(denomination, amount);
        }
        return new Money(newMoneyMap);
    }

    public Money remove(int denomination) {
        if(!DENOMINATION_LIST.contains(denomination)) {
            throw new IllegalArgumentException("Not a valid denomination");
        }
        if((denominationAmounts.get(denomination) < 1)){
            throw new IllegalArgumentException("There are no units of that" +
                    "denomination (" + denomination + ")");
        }
        denominationAmounts.put(denomination, denominationAmounts.get(denomination) - 1);

        return new Money(this.getDenominationAmounts());
    }

    public Money remove(Money oldMoney) {
        HashMap<Integer, Integer> newMoneyMap = new HashMap<>();

        for (int denomination : oldMoney.getDenominationAmounts().keySet()) {
            int amount;
            amount = this.denominationAmounts.get(denomination) - oldMoney.getDenominationAmounts().get(denomination);
            newMoneyMap.put(denomination, amount);
        }
        return new Money(newMoneyMap);
    }
}
