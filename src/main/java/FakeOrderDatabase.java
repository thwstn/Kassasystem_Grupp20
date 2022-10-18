import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeOrderDatabase implements OrderDatabaseIO{
    ArrayList<Order> orderData = new ArrayList<>();
    private static final Employee JOHN = new Employee ("John", 25000);
    private static final Employee AMANDA = new Employee ("Amanda", 27000);
    private static final Employee THEO = new Employee ("Theo", 32000);

    private static final Customer MARY = new Customer(UUID.randomUUID(), "Mary", 45);
    private static final Customer TRACY = new Customer(UUID.randomUUID(), "Tracy", 23);
    private static final Customer HILDA = new Customer(UUID.randomUUID(), "Hilda", 84);

    private static final Order O1 = new Order(JOHN, MARY,
    new OrderLine("Cucumber", 5.0, 3),
    new OrderLine("Tomato", 12.0, 8),
    new OrderLine("Pasta", 12.90, 3));

    private static final Order O2 = new Order(AMANDA, MARY,new OrderLine("Mjölk", 5.0, 8),
    new OrderLine("Pasta", 12.0, 2),
    new OrderLine("Tomat", 9.0, 10),
    new OrderLine("Kikärtor", 4.0, 7),
    new OrderLine("Köttfärs", 5.0, 3));

    private static final Order O3 = new Order(THEO, MARY,
    new OrderLine("Mjölk", 5.0, 2),
    new OrderLine("Grädde", 5.0, 1),
    new OrderLine("Pasta", 12.0, 8),
    new OrderLine("Smör", 5.0, 12));
    private static final Order O4 = new Order(JOHN, TRACY,
    new OrderLine("Pasta", 12.0, 5),
    new OrderLine("Smör", 5.0, 11),
    new OrderLine("Krossade tomater", 5.0, 4),
    new OrderLine("Pasta", 5.0, 10));
    private static final Order O5 = new Order(THEO, HILDA,
    new OrderLine("Mjölk", 5.0, 2),
    new OrderLine("Pasta", 12.0, 5),
    new OrderLine("Tomat", 9.0, 12),
    new OrderLine("Kikärtor", 4.0, 2),
    new OrderLine("Köttfärs", 5.0, 9),
    new OrderLine("Mjölk", 5.0, 3),
    new OrderLine("Grädde", 5.0, 1));
    private static final Order O6 = new Order(THEO, TRACY,
    new OrderLine("Kikärtor", 4.0, 7),
    new OrderLine("Köttfärs", 5.0, 3),
    new OrderLine("Mjölk", 5.0, 2),
    new OrderLine("Grädde", 5.0, 1),
    new OrderLine("Pasta", 12.0, 8),
    new OrderLine("Smör", 5.0, 12),
    new OrderLine("Krossade tomater", 5.0, 4),
    new OrderLine("Pasta", 5.0, 3));
    private static final Order O7 = new Order(AMANDA, HILDA,
    new OrderLine("Tomat", 9.0, 2),
    new OrderLine("Kikärtor", 4.0, 12),
    new OrderLine("Köttfärs", 5.0, 200),
    new OrderLine("Mjölk", 5.0, 4),
    new OrderLine("Grädde", 5.0, 2),
    new OrderLine("Pasta", 12.0, 3),
    new OrderLine("Smör", 5.0, 2));

    @Override
    public Order getOrderFromID(UUID uuid) {
        return null;
    }

    @Override
    public void fillDatabase() {
        orderData.addAll(List.of(O1,O2,O3,O4,O5,O6,O7));
    }
}
