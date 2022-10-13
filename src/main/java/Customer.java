import java.util.ArrayList;

public class Customer {
    private int customerID;
    private String name;
    private int age;
    private ArrayList<Order> orders;

    public Customer(int customerID, String name, int age) {
        this.customerID = customerID;
        this.name = name;
        this.age = age;
        this.orders = new ArrayList<>();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void increaseAge() {
        age++;
    }
    public void changeName(String newName) {
        name = newName;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
