import java.util.UUID;

public interface OrderDatabaseIO {

    Order getOrderFromID(UUID uuid);

    void fillDatabase();
}
