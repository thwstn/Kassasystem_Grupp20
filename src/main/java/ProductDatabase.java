import java.util.ArrayList;
import java.util.HashMap;

public interface ProductDatabase {

    Product getProductFromDatabase(EAN ean);


    Product getProductFromDatabase(String name);






}
