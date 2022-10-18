import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductGroup {
    private static final List<String> PRODUCT_GROUPS = List.of("Fruit&Vegetables", "Dairy", "Meat&Poultry", "Dry");

    private String productGroupName;
    private VAT vat;

    private ArrayList<Product> productList = new ArrayList<>();


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
    public Product getProductByName(String name) {
        for (String s : PRODUCT_GROUPS) {
            for (Product p : productList) {
                if (p.getName().equals(name)) {
                    return p;
                }
            }
        }
        throw new IllegalArgumentException("Product does not exist!");
    }
    public Product getProductByEan(EAN ean){
        for (String s : PRODUCT_GROUPS){
            for(Product p:productList){
                if(p.getEan().equals(ean)){
                    return p;
                }
            }
        }
        throw new IllegalArgumentException("Product does not exist!");
    }
    public ArrayList<Product> getAllProducts(){
        return productList;
    } //ny lista med produkter,
    //removeProduct metod, kanske genom att ta in EAN från checkout
    //hämta listan, eller skriva in alla produkter direkt i klassen?
    //nästan loop, för att leta efter produkt i produktrupperna som sedan kollar igenom dens produkter
    //hitta produkt returnera pris och namn, genom EAN som parameter, kanske en map med namn och pris
    //göra en contains metod

    public boolean containsProductByEan(EAN ean) {
        for (String productGroup : PRODUCT_GROUPS) {
            for (Product product : productList) {
                if (productList.contains(product)) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Product does not exist!");
                }
            }
        }return false;
    }
    public boolean containsProductByName(String productName){
        for (String productGroup: PRODUCT_GROUPS) {
            for (Product product : productList){
                if(productList.contains(product)){
                    return true;
                }else {
                    throw new IllegalArgumentException("Product does not exist!");
                }
            }
        }return false;
    }
    public void removeProductByName(String product,int saldoToDecrease) {
        for (String s : PRODUCT_GROUPS) {
            for (Product p : productList) {
                if (p.getName().equalsIgnoreCase(product)) {
                    p.decreaseAmount(saldoToDecrease);
                    productList.remove(product);
                }
            }
        }
    }
    public void removeProductByEAN(EAN ean, int saldoToDecrease) {
        for (String s : PRODUCT_GROUPS) {
            for (Product p : productList) {
                if (p.getEan().equals(ean)) {
                    p.decreaseAmount(saldoToDecrease);
                }
            }
        }
    }
    //degreaseSaldo
    //removeProductEAN
    //removeProductName
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
