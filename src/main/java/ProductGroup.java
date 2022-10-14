import java.util.ArrayList;
import java.util.HashMap;

public class ProductGroup {
    private ProductGroup productGroup;
    public VAT moms;
    public ProductGroup Vegetables, Dairy, Fresh, Soda;
    ArrayList<ProductGroup> Pgroup = new ArrayList<>();
    public void setVAT(){
    }
   public ProductGroup getProductGroup() {
        return productGroup; //Ska finnas i Product
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
        }
    ProductGroup(ProductGroup productGroup, VAT.VATCategories vat) {
    this.productGroup = productGroup;
    }

    ProductGroup(VAT vat) {
        moms = vat;
    }
    public void addPGroup(ProductGroup productGroup){
        Pgroup.add(productGroup);
    }
    public ProductGroup changeCategoryName(ProductGroup productgroup, ProductGroup BetterProductgroup){
        return BetterProductgroup;
    }

    public boolean productExists() {
        return false;
    }

    public VAT getVat() {
        return moms;
    }


}
