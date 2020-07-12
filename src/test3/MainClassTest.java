package test3;

import org.junit.Assert;
import org.junit.Test;


public class MainClassTest extends MainClass {

    @Test
    public void testGetClassString() {

        String str = this.getClassString();
        if (str.contains("hello"))
        {

        } else if (str.contains("Hello")) {

        } else {
            Assert.fail("\nТест \"fail\" потому, что нет ни одной из подстрок \"hello\" или \"Hello\"");
        }

    }
}
