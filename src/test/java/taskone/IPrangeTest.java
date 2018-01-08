package taskone;

import org.junit.*;
import java.io.*;
import java.util.Objects;

import static org.junit.Assert.*;

public class IPrangeTest {

    IPrange iprange;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public  void SetUp () {
        iprange = new IPrange();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public  void CleanUp () {
        iprange = null;
        System.setOut(null);
    }

    @Test
    public void TestShowRange() {
        try {
            iprange.ShowRange("192.168.0.1","192.168.0.5");
        }
        catch (Exception ex){
            fail("Что-то сломалось при обработке корректных значений");
        }
        assertEquals("192.168.0.2\n192.168.0.3\n192.168.0.4\n", outContent.toString());
    }

    @Test
    public void TestNullShowRange() {
        boolean result = true;
        try {
            iprange.ShowRange(null,"192.168.0.5");
            iprange.ShowRange("192.168.0.1",null);
            iprange.ShowRange(null,null);
        }
        catch (Exception ex){
            if (!Objects.equals(ex.getMessage(), "Ошибка: пустое значение"))
                result = false;
        }
        assertTrue("Тест на пустое значение не пройден", result);
    }

    @Test
    public void TestIp1GTIp2ShowRange() {
        boolean result = true;
        try {
            iprange.ShowRange("192.168.0.5","192.168.0.1");
        }
        catch (Exception ex){
            if (!Objects.equals(ex.getMessage(), "Ошибка: ip1 > ip2"))
                result = false;
        }
        assertTrue("Тест на ip1>ip2 не пройден", result);
    }

    @Test
    public void TestIPtoLong() {
        try {
            iprange.IPtoLong("192.168.0.1");
        }
        catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void TestLongIPtoString() {
        try {
            iprange.LongIPtoString(3232235521L);
        }
        catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void TestIPtoLongAndLongIPtoString () {
        String testValue = "192.168.0.1";
        String result = null;
        long resultLong = iprange.IPtoLong(testValue);
        result = iprange.LongIPtoString(resultLong);
        assertEquals(testValue, result);

    }
}