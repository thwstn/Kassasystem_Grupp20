import java.util.ArrayList;
import java.util.HashMap;

public interface ProductDatabase {
    ArrayList<Product> productData = new ArrayList<>();


    HashMap<String,Double> getProductForOrderLine(EAN ean);





}
