import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductGroup {
    private static final List<String> PRODUCT_GROUPS = List.of("Fruit&Vegetables", "Dairy", "Meat&Poultry", "Dry");

    private String productGroupName;
    private VAT vat;

    ArrayList<Product> productList = new ArrayList<>();


    ProductGroup(String productGroupName, VAT.VATCategories vatCategory) {
        if(PRODUCT_GROUPS.contains(productGroupName)){
            this.productGroupName = productGroupName;
            this.vat = new VAT(vatCategory);
        }else {
            throw new IllegalArgumentException("Not a valid product group!");
        }
    }
    public String getProductGroupName() {
        return productGroupName;
    }
    public ArrayList<Product> getAllProducts(){
        return productList;
    }
    public VAT getVAT() {
        return vat;
    }
    public void addProduct(Product product){
        productList.add(product);
    }
    public void changeCategoryName(String newProductGroupName){
        if(PRODUCT_GROUPS.contains(newProductGroupName)){
            this.productGroupName = newProductGroupName;
        }else {
            throw new IllegalArgumentException("Not a valid product group!");
        }

    }
    private List<String> productGroupsList(){
        return PRODUCT_GROUPS;
    }
    private void sortListOfProducts(){
        List<Product>sortedList = productList.stream().sorted().toList();
        sortedList.forEach(System.out::println);
    }
    @Override
    public String toString() {
        return "ProductGroup: " +productGroupName + ',' + "VAT: " +
                vat + "\n"
                ;
    }
    /*public boolean productExists() {
        return false;
    }*/
    //strukturera upp koden
    //Sortera listan, hitta en vara i listan genom bokstav eller pris
}
