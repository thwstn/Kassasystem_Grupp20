import java.util.IllegalFormatException;

public class EAN {
    private final long eanCode;

    public EAN(long eanCode) {
        if (String.valueOf(eanCode).length() < 12) {
            throw new IllegalArgumentException("The EAN code is too short!");
        } else if (String.valueOf(eanCode).length() > 13) {
            throw new IllegalArgumentException("The EAN code is too long!");
        }
        this.eanCode = eanCode;
    }

    public long getEANCode() {
        return eanCode;
    }
}