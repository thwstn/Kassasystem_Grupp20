
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


class FakeCustomerDatabase {
    ArrayList<Customer> customerList = new ArrayList<>();
    private static final Customer THEO = new Customer("Theo", 32);
    private static final Customer NIKLAS = new Customer("Niklas", 25);
    private static final Customer JOHAN = new Customer("Johan", 27);
    private static final Customer JACOB = new Customer("Jacob", 27);
    private static final Customer DAVID = new Customer("David", 31);

    public void fillDB() {
        customerList.addAll(List.of(THEO, NIKLAS, JOHAN, DAVID, JACOB));
    }

    public Customer getCustomerID(UUID uuid){
        for(Customer c : customerList){
            if(c.getCustomerID().equals(uuid)){
                return c;
            }
        }return null;
    }
    public Customer getCustomerNameAndAge(String name,int age){
        for(Customer c: customerList){
            if(c.getName().equalsIgnoreCase(name)&&c.getAge()== age){
                return c;
            }
        }return null;
    }
}