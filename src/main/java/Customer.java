import java.util.Objects;
import java.util.UUID;

public class Customer implements Comparable<Customer> {
    private final UUID customerID;
    private String name;
    private int age;
    //private ArrayList<Order> orders;

    public Customer(String name, int age) {
        //this.customerID = customerID;
        this.name = name;
        this.age = age;
        this.customerID = UUID.randomUUID();
        //this.orders = new ArrayList<>();
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    //public ArrayList<Order> getOrders() {
    //    return orders;
    //}

    public void increaseAge() {
        age++;
    }
    public void changeName(String newName) {
        name = newName;
    }

    /*public void addOrder(Order order) {
        orders.add(order);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Customer o) {
        return this.getName().compareTo(o.getName());
    }
}
