import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeOrderDatabase implements OrderDatabaseIO{
    ArrayList<Order> orderData = new ArrayList<>();

    private static final Employee ANNA = new Employee("Anna", 28000);
    private static final Employee CALLE = new Employee("Calle", 35000);
    private static final Employee DANIELLA = new Employee("Daniella", 16000);

    private static final Customer THEO = new Customer("Theo", 32);
    private static final Customer JACOB = new Customer("Jacob", 27);
    private static final Customer JOHAN = new Customer("Johan", 27);


    private static final Order O1 = new Order(ANNA, THEO,
    new OrderLine("Cucumber", 5.0, 3),
    new OrderLine("Tomato", 12.0, 8),
    new OrderLine("Pasta", 12.90, 3));

    private static final Order O2 = new Order(ANNA, JACOB,new OrderLine("Mjölk", 5.0, 8),
    new OrderLine("Pasta", 12.0, 2),
    new OrderLine("Tomato", 9.0, 10),
    new OrderLine("Chickpeas", 4.0, 7),
    new OrderLine("Minced Meat", 5.0, 3));

    private static final Order O3 = new Order(CALLE, JOHAN,
    new OrderLine("Milk", 5.0, 2),
    new OrderLine("Cream", 5.0, 1),
    new OrderLine("Pasta", 12.0, 8),
    new OrderLine("Butter", 45.0, 12));
    private static final Order O4 = new Order(DANIELLA, JACOB,
    new OrderLine("Pasta", 12.0, 5),
    new OrderLine("Butter", 45.0, 21),
    new OrderLine("Crushed Tomatoes", 8.0, 4),
    new OrderLine("Pasta", 5.0, 10));
    private static final Order O5 = new Order(CALLE, THEO,
    new OrderLine("Milk", 5.0, 2),
    new OrderLine("Pasta", 12.0, 5),
    new OrderLine("Tomato", 9.0, 12),
    new OrderLine("Chickpeas", 4.0, 2),
    new OrderLine("Minced Meat", 5.0, 9),
    new OrderLine("Milk", 5.0, 3),
    new OrderLine("Cream", 5.0, 1));
    private static final Order O6 = new Order(ANNA, JOHAN,
    new OrderLine("Chickpeas", 4.0, 7),
    new OrderLine("Minced Meat", 5.0, 3),
    new OrderLine("Milk", 5.0, 2),
    new OrderLine("Cream", 5.0, 1),
    new OrderLine("Pasta", 12.0, 8),
    new OrderLine("Butter", 45.0, 12),
    new OrderLine("Crushed Tomatoes", 8.0, 4),
    new OrderLine("Pasta", 5.0, 3));
    private static final Order O7 = new Order(DANIELLA, THEO,
    new OrderLine("Tomato", 9.0, 2),
    new OrderLine("Chickpeas", 4.0, 12),
    new OrderLine("Minced Meat", 5.0, 200),
    new OrderLine("Milk", 5.0, 4),
    new OrderLine("Cream", 5.0, 2),
    new OrderLine("Pasta", 12.0, 3),
    new OrderLine("Butter", 45.0, 2));

    public FakeOrderDatabase(){
        fillDatabase();
    }

    @Override
    public ArrayList<Order> getAllOrdersByCustomer(Customer customer) {
        ArrayList<Order> newList = new ArrayList<>();
        for (Order o:orderData) {
            if(o.getCustomer().equals(customer)){
                newList.add(o);
            }
        }
        return newList;
    }

    @Override
    public void fillDatabase() {
        orderData.addAll(List.of(O1,O2,O3,O4,O5,O6,O7));
    }
}
