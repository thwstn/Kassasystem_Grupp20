public class VAT {
    private VATCategories vatCategory;
    public VAT(VATCategories vat) {
        this.vatCategory = vat;
    }

    enum VATCategories {
        VAT25,
        VAT12,
        VAT6;
    }
        public double getPercent() {
            return switch (vatCategory) {
                case VAT25 -> 0.25;
                case VAT12 -> 0.12;
                case VAT6 -> 0.06;
            };
        }
}