import com.deliveroo.expression.CronExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CronExpressionTest {

    @Test
    public void cronExpressionWithInvalidRangeValue() {
        String[] testArgs = {"*/15", "0", "1,15", "*", "1-5-8", "/usr/bin/find"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CronExpression(testArgs));
        assertEquals("Value format is not valid for Cron DateTime type: dayOfWeek", exception.getMessage());
    }

    @Test
    public void cronExpressionWithInvalidFreqValue() {
        String[] testArgs = {"*/15/20", "0", "1,15", "*", "1-5", "/usr/bin/find"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CronExpression(testArgs));
        assertEquals("Value format is not valid for Cron DateTime type: minute", exception.getMessage());
    }

    @Test
    public void parseCronExpressionWithValidArgs() {
        String expectedString = "minute        0 15 30 45\n" +
                "hour          0\n" +
                "dayOfMonth    1 15\n" +
                "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "dayOfWeek     1 2 3 4 5\n" +
                "command       /usr/bin/find\n";
        String[] testArgs = {"*/15", "0", "1,15", "*", "1-5", "/usr/bin/find"};
        assertEquals(expectedString, new CronExpression(testArgs).toString());
    }

    @Test
    public void parseCronExpressionWithVariations() {
        String expectedString = "minute        0 15 30 45\n" +
                "hour          0 3 4 5 8\n" +
                "dayOfMonth    1 2 3 4 5 6 7 8 9 10 11 12 13 14 15\n" +
                "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "dayOfWeek     1 3 5\n" +
                "command       /usr/bin/find\n";
        String[] testArgs = {"*/15", "0,3,4,5,8", "1-15", "*", "1-5/2", "/usr/bin/find"};
        assertEquals(expectedString, new CronExpression(testArgs).toString());
    }
}