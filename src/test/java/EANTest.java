import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EANTest {

    @Test
    void EANIsTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            EAN ean = new EAN(123456789);
        });
    }
    @Test
    void getEanTestReturnsCorrectValue(){
        EAN ean = new EAN(123123123123L);
        assertEquals(123123123123L, ean.getEANCode());
    }
}
