import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;


public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
//        Win path
        capabilities.setCapability("app", "C:\\Users\\Vadim\\IdeaProjects\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
//        MacOS path
//        capabilities.setCapability("app", "/Users/admin/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }


    @Test
    public void testSearchInputPlaceholderText() {

        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input"
        );

        assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "The input field does not contain the search text"
        );
    }


    @Test
    public void testCancelAndClearSearch () {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Windows",
                "Cannot find search input",
                5
        );
        
        int result = getSearchArticleCount(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "There is no search results on the screen",
                10
        );

        Assert.assertTrue("Not found search results on the screen", result > 0);

        waitForElementAndClear(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Cannot find search input",
                5
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/fragment_search_results']"),
                "Search results are displayed on the screen",
                5
        );

    }


    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    private WebElement waitForElementPresent(By by, String errorMessage) {

        return waitForElementPresent(by, errorMessage, 5);
    }


    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }


    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }


    private void assertElementHasText(By by, String expectedText, String errorMessage) {

        WebElement element = waitForElementPresent(by, errorMessage);
        String elementText = element.getAttribute("text");
        Assert.assertEquals(
                errorMessage,
                expectedText,
                elementText
        );
    }


    private int getSearchArticleCount(By by, String errorMessage, long timeoutInSeconds) {

        List<WebElement> searchResults = waitForListOfElementsPresentByXPath(by, errorMessage, timeoutInSeconds);
        return searchResults.size();
    }


    private WebElement getSearchArticleCount(By by, String errorMessage) {

        return waitForElementPresent(by, errorMessage, 5);
    }


    private List<WebElement> waitForListOfElementsPresentByXPath(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }


    private WebElement waitForListOfElementsPresentByXPath(By by, String errorMessage) {

        return waitForElementPresent(by, errorMessage, 5);
    }
}

