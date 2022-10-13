public class VAT {
    enum VATCategories {
        VAT25,
        VAT12,
        VAT6;
    }

    public double getPercent() {
        return percent;
    }
    private double percent;
    VAT(VATCategories vat) {
        switch (vat) {
            case VAT25:
                percent = 0.25;
            case VAT12:
                percent = 0.12;
            case VAT6:
                percent = 0.06;
        }
    }
}