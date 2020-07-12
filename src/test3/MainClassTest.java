package test3;

import org.junit.Assert;
import org.junit.Test;


public class MainClassTest extends MainClass {

    @Test
    public void testGetClassString() {

        Assert.assertTrue("\nТест \"fail\" потому, что нет ни одной из подстрок \"hello\" или \"Hello\".",  this.getClassString().contains("hello") || this.getClassString().contains("Hello"));

    }
}
