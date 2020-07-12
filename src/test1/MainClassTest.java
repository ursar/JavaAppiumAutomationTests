package test1;
import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {

        int expectedNumber = 14;
        int actualNumber;


        MainClass number = new MainClass();
        actualNumber = number.getLocalNumber();

        Assert.assertEquals("Тест \"fail\" потому что: ", expectedNumber, actualNumber);

//        or use "assertTrue"
//        Assert.assertTrue("Тест \"fail\" потому что: \nОжидаемое значение: " + expectedNumber + "\nФактическое значение: " + actualNumber, expectedNumber == actualNumber);



    }
}
