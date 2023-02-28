package src;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void test_isValid_daysInFeb_nonLeap(){
        Date date = new Date("2/29/2003");
        assertFalse(date.isValid());
    }
    @Test
    public void test_isValid_daysInFeb_nonValid() {
        Date date = new Date("4/31/2003");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_daysInFeb_monthInvalid(){
        Date date = new Date("13/31/2003");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_dayInvalid(){
        Date date = new Date("3/32/2003");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_negativeMonth() {
        Date date = new Date("-1/31/2003");
        assertFalse(date.isValid());
    }

    @Test
    public void test_isValid_daysInFeb_validDate() {
        Date date = new Date("2/28/2004");
        assertTrue(date.isValid());
    }

    @Test
    public void test_isValid_daysInMarch_validDates() {
        Date date = new Date("3/20/2003");
        assertTrue(date.isValid());
    }
}