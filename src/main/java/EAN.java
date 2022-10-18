import java.util.IllegalFormatException;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EAN ean = (EAN) o;
        return eanCode == ean.eanCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eanCode);
    }
}