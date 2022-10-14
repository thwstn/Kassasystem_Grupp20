public class VAT {
    enum VATCategories {
        VAT25,
        VAT12,
        VAT6;

        public static boolean contains(VATCategories vat) {
            if(vat==VAT12 || vat==VAT25 || vat==VAT6) {
                return true;
            }else {
                throw new IllegalArgumentException("Wrong VAT, must be 6, 12 or 25");
            }
        }
    }

    public VATCategories getPercent(ProductGroup productGroup) {
        return productGroup.vat;
    }
}