package test2;
import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {

        int expectedClassNumber = 45;
        int actualClassNumber;

        MainClass number = new MainClass();
        actualClassNumber = number.getClassNumber();

        Assert.assertTrue("\nТест \"fail\" потому что: " + actualClassNumber + " <= " + expectedClassNumber, actualClassNumber > expectedClassNumber);

    }
}
