import java.util.HashMap;

public class Money {
    HashMap<Integer, Integer> denominations;

    public Money() {
        HashMap<Integer, Integer> d = new HashMap<>();
        d.put(100000,0);
        d.put(50000,0);
        d.put(20000,0);
        d.put(10000,0);
        d.put(5000,0);
        d.put(2000,0);
        d.put(1000,0);
        d.put(500,0);
        d.put(200,0);
        d.put(100,0);
    }

    public Money(HashMap<Integer, Integer> denominations) {
        this.denominations = denominations;
    }

    public HashMap<Integer, Integer> getDenominations() {
        return denominations;
    }
}
