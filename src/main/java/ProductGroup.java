import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductGroup {
    private static final List<String> PRODUCT_GROUPS = List.of("Fruit&Vegetables", "Dairy", "Meat&Poultry", "Dry");
    /*public static final ProductGroup VEGETABLES = new ProductGroup("VEGETABLES", VAT.VATCategories.VAT6);
    public static final ProductGroup FRESH = new ProductGroup("FRESH", VAT.VATCategories.VAT12);
    public static final ProductGroup DRINKS = new ProductGroup("DRINKS", VAT.VATCategories.VAT6);
    public static final ProductGroup MEAT = new ProductGroup("MEAT", VAT.VATCategories.VAT25);
*/
    public String productGroupName;
    public VAT.VATCategories vat;
    ArrayList<Product> productList = new ArrayList<>();
    public void setVAT(VAT.VATCategories vat){
        this.vat = vat;
    }


   /* public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup; //ska finnas i product?
        }*/
    ProductGroup(String productGroupName, VAT.VATCategories vat) {
        if(PRODUCT_GROUPS.contains(productGroupName) && VAT.VATCategories.contains(vat)){
            this.productGroupName = productGroupName;
            this.vat = vat;
        }else {
            throw new IllegalArgumentException("Not a valid Product group!");
        }
    }

    ProductGroup(VAT vat) {
        vat = vat;
    }

    public void changeCategoryName(String oldNameOfProductGroup, String newNameOfProductGroup){
        this.productGroupName = productGroupName;

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
        return new ArrayList<>();
    }

    public boolean productExists() {
        return false;
    }

    /*public VAT getVat() {
        return vat;
    }*/


}
