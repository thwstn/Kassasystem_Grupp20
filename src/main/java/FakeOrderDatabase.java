import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeOrderDatabase implements OrderDatabaseIO{
    private static final FakeEmployeeDatabase FED = new FakeEmployeeDatabase();
    private static final FakeCustomerDatabase FCD = new FakeCustomerDatabase();
    ArrayList<Order> orderData = new ArrayList<>();

    private static final Employee ANNA = FED.getEmployee("Anna");
    private static final Employee CALLE = FED.getEmployee("Calle");
    private static final Employee DANIELLA = FED.getEmployee("Daniella");

    private static final Customer THEO = FCD.getCustomer("Theo");
    private static final Customer JACOB = FCD.getCustomer("Jacob");
    private static final Customer JOHAN = FCD.getCustomer("Johan");


    private static final Order O1 = new Order(ANNA, THEO,
    new OrderLine("Cucumber", 5.0, 3),
    new OrderLine("Tomato", 12.0, 8),
    new OrderLine("Pasta", 12.90, 3));

    private static final Order O2 = new Order(ANNA, JACOB,new OrderLine("Mjölk", 5.0, 8),
    new OrderLine("Pasta", 12.0, 2),
    new OrderLine("Tomat", 9.0, 10),
    new OrderLine("Kikärtor", 4.0, 7),
    new OrderLine("Köttfärs", 5.0, 3));

    private static final Order O3 = new Order(CALLE, JOHAN,
    new OrderLine("Mjölk", 5.0, 2),
    new OrderLine("Grädde", 5.0, 1),
    new OrderLine("Pasta", 12.0, 8),
    new OrderLine("Smör", 5.0, 12));
    private static final Order O4 = new Order(DANIELLA, JACOB,
    new OrderLine("Pasta", 12.0, 5),
    new OrderLine("Smör", 5.0, 11),
    new OrderLine("Krossade tomater", 5.0, 4),
    new OrderLine("Pasta", 5.0, 10));
    private static final Order O5 = new Order(CALLE, THEO,
    new OrderLine("Mjölk", 5.0, 2),
    new OrderLine("Pasta", 12.0, 5),
    new OrderLine("Tomat", 9.0, 12),
    new OrderLine("Kikärtor", 4.0, 2),
    new OrderLine("Köttfärs", 5.0, 9),
    new OrderLine("Mjölk", 5.0, 3),
    new OrderLine("Grädde", 5.0, 1));
    private static final Order O6 = new Order(ANNA, JOHAN,
    new OrderLine("Kikärtor", 4.0, 7),
    new OrderLine("Köttfärs", 5.0, 3),
    new OrderLine("Mjölk", 5.0, 2),
    new OrderLine("Grädde", 5.0, 1),
    new OrderLine("Pasta", 12.0, 8),
    new OrderLine("Smör", 5.0, 12),
    new OrderLine("Krossade tomater", 5.0, 4),
    new OrderLine("Pasta", 5.0, 3));
    private static final Order O7 = new Order(DANIELLA, THEO,
    new OrderLine("Tomat", 9.0, 2),
    new OrderLine("Kikärtor", 4.0, 12),
    new OrderLine("Köttfärs", 5.0, 200),
    new OrderLine("Mjölk", 5.0, 4),
    new OrderLine("Grädde", 5.0, 2),
    new OrderLine("Pasta", 12.0, 3),
    new OrderLine("Smör", 5.0, 2));

    public FakeOrderDatabase(){
        fillDatabase();
    }

    @Override
    public Order getOrderFromID(UUID uuid) {
        return null;
    }

    @Override
    public void fillDatabase() {
        orderData.addAll(List.of(O1,O2,O3,O4,O5,O6,O7));
    }
}
