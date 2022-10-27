import java.util.*;
import java.util.stream.Collectors;
public class Statistics {

    private final EmployeeDatabase employeeDatabase;
    private final OrderDatabaseIO orderDatabase;
    private final ProductDatabase productDatabase;
    private final CheckOutSessionDatabase checkOutSessionDatabase;

    public Statistics(EmployeeDatabase employeeDatabase, OrderDatabaseIO orderDatabase, ProductDatabase productDatabase,
                      CheckOutSessionDatabase checkOutSessionDatabase) {
        this.employeeDatabase = employeeDatabase;
        this.orderDatabase = orderDatabase;
        this.productDatabase = productDatabase;
        this.checkOutSessionDatabase = checkOutSessionDatabase;
    }

   public int getAverageSalary() {
        int totalSalary = 0;
        int counter = 0;
        for (Employee e : employeeDatabase.get()) {
            totalSalary = totalSalary + e.getSalary();
            counter++;
        }
        return totalSalary / counter;
    }

    public Product getCustomerMostBoughtProduct(Customer customer) {
        Order tempOrder = new Order(new Employee("Theo", 12_000));
        List<Order> customerOrders = orderDatabase.getAllOrdersByCustomer(customer);
        for (Order order : customerOrders) {
            Collection<OrderLine> ol = order.getOrderLineList();
            for (OrderLine orderLine : ol) {
                tempOrder.addOrderLineToList(new OrderLine(orderLine.getName(), orderLine.getPrice(), orderLine.getQuantity()));
            }
        }
        tempOrder.groupAllOrderLinesTogether();
        String product = "";
        int highestAmount = 0;
        for (OrderLine orderLine : tempOrder.getOrderLineList()) {
            if (orderLine.getQuantity() > highestAmount) {
                highestAmount = orderLine.getQuantity();
                product = orderLine.getName();
            }
        }
        return productDatabase.getProductFromDatabase(product);
    }


    public LinkedHashMap<String, Integer> getEmployeesBySpeed() {
        // Antal produkter per employee
    HashMap<String, Integer> soldProducts = new HashMap<>();
    for (Order order : orderDatabase.getAllOrders()) {
        Employee employee = order.getEmployee();
        int amountOfProducts = 0;
        for (OrderLine orderLine : order.getOrderLineList()) {
            amountOfProducts = amountOfProducts + orderLine.getQuantity();
        }
        if (soldProducts.get(employee.getName()) == null) {
            soldProducts.put(employee.getName(), amountOfProducts);
        } else {
            soldProducts.put(employee.getName(), soldProducts.get(employee.getName()) + amountOfProducts);
        }

    }
        // Antal arbetad tid per employee
    HashMap<String, Integer> workedSeconds = new HashMap<>();
    for (Employee employee : employeeDatabase.get()) {
        int totalTime = 0;
        for (CheckOutSession checkOutSession : checkOutSessionDatabase.getCheckOutSessionFromDatabaseWithEmployee(employee)) {
            totalTime = totalTime + checkOutSession.getSessionLenghtInSeconds();
        }
        workedSeconds.put(employee.getName(), totalTime);
    }
        // Ett värde som ett resultat av tid delat på produkter
    HashMap<String, Integer> employeesSoldProductsPerSecond = new HashMap<>();
    for (Employee employee : employeeDatabase.get()) {
        int products = soldProducts.get(employee.getName());
        int time = workedSeconds.get(employee.getName());
        int speed = time / products;
        employeesSoldProductsPerSecond.put(employee.getName(), speed);
        }

        // Returnera en map sorterad på speed
    LinkedHashMap<String, Integer> employeesWithSpeedSorted = new LinkedHashMap<>();
    employeesSoldProductsPerSecond.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x -> employeesWithSpeedSorted.put(x.getKey(), x.getValue()));
    return employeesWithSpeedSorted;
    }


    public int getAverageCheckOutSessionLength(Employee employee, CheckOutSessionDatabase fos) {
        ArrayList<CheckOutSession> checkOutSessions = fos.getCheckOutSessionFromDatabaseWithEmployee(employee);
        int totalSeconds = 0;
        int counter = 0;
        for (CheckOutSession checkOutSession : checkOutSessions) {
            totalSeconds = totalSeconds + checkOutSession.getSessionLenghtInSeconds();
            counter++;
        }
        return totalSeconds / counter;
    }

    public Map<String, Integer> getTopFiveSoldProductsEver(){
        Collection<Order> allOrders = orderDatabase.getAllOrders();
        TreeMap<String, Integer> topFive = new TreeMap<>(Collections.reverseOrder());
        Order combinedOrder = new Order(new Employee("NA", 0));
        for (Order o : allOrders) {
            Collection<OrderLine> currentOrderLines = o.getOrderLineList();
            for (OrderLine ol : currentOrderLines) {
                combinedOrder.addOrderLineToList(ol);
            }
        }
        combinedOrder.groupAllOrderLinesTogether();
        combinedOrder.sortByQuantityHighestToLowest();
        for(int i = 0; i < 5; i++){
            Product p = productDatabase.getProductFromDatabase(combinedOrder.getOrderLineAtIndex(i).getName());
            int quantity = combinedOrder.getOrderLineAtIndex(i).getQuantity();
            topFive.put(p.getName(), quantity);
        }
        return topFive.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map.Entry<Customer, Integer> getCustomerWithMostOrders() {
        TreeMap<Customer, Integer> allCustomers = new TreeMap<>();
        Collection<Order> allOrders = orderDatabase.getAllOrders();
        for (Order o : allOrders) {

            if(allCustomers.containsKey(o.getCustomer())){
                allCustomers.put(o.getCustomer(), allCustomers.get(o.getCustomer()) + 1);
            }
            else{
                allCustomers.put(o.getCustomer(), 1);
            }
        }
        return Collections.max(allCustomers.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

    }
}
