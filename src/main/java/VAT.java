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
        //Ska det verkligen vara ENUM? Måste ha ett värde?

        public double getPercent() {       //Ändra till switch-sats
            switch (vatCategory) {
                case VAT25:
                    return 0.25;
                case VAT12:
                    return 0.12;
                case VAT6:
                    return 0.06;
            }return 0;
        }
             //Ska inte vara return 0, ändra på det efter tester.
            //göra en if-sats för att se vilken procentsats som ska tilldelas?
            //Hur gör man när det är ENUMS?//fel, bara för att kunna köra andra tester.

}