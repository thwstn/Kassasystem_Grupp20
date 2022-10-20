import java.util.ArrayList;
import java.util.HashMap;

public interface ProductDatabase {

    Product getProductFromDatabase(EAN ean);


    Product getProductFromDatabase(String name);

    void addProductToDatabase(Product product);

    void removeProductFromDatabase(Product product);








}
