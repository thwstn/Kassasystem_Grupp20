import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CheckOutSessionTest {
    @Test
    void addEndDateToSession() {
        CheckOutSession checkOutSession = new CheckOutSession(123);
        checkOutSession.quitSession();
        assertEquals(true, checkOutSession.endDate != null, "End Date does not exist!");
    }
}
