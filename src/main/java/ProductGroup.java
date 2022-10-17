import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductGroup {
    private static final List<String> PRODUCT_GROUPS = List.of("Fruit&Vegetables", "Dairy", "Meat&Poultry", "Dry");

    public String productGroupName;
    private VAT vat;

    ArrayList<Product> productList = new ArrayList<>();
    public void addProduct(Product product){
        productList.add(product);
    }

    ProductGroup(String productGroupName, VAT.VATCategories vatCategory) {
        if(PRODUCT_GROUPS.contains(productGroupName)){
            this.productGroupName = productGroupName;
            this.vat = new VAT(vatCategory);
        }else {
            throw new IllegalArgumentException("Not a valid Product group!");
        }
    }

    public void changeCategoryName(String newProductGroupName){
        this.productGroupName = newProductGroupName;

    }
    public List<String> productGroupsList(){
        return PRODUCT_GROUPS;
    }

    @Override
    public String toString() {
        return "ProductGroup: " +productGroupName + ',' + "VAT: " +
                vat + "\n"
                ;
    }

    public ArrayList<Product> getAllProducts(){  //Filen som parameter?
        return productList;
    }

    /*public boolean productExists() {
        return false;
    }*/
    public VAT getVAT() {
        return vat;
    }

    public String getProductGroupName() {
        return productGroupName;
    }
}
