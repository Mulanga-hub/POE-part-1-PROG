import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {

    @Test
    public void testUsername() {
        assertTrue(Validation.checkUsername("abc_d"));
    }

    @Test
    public void testPassword() {
        assertTrue(Validation.checkPassword("Password1!"));
    }

    @Test
    public void testCellPhone() {
        assertTrue(Validation.checkCellPhone("+27831234567"));
    }
}