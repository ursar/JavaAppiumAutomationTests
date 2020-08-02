import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
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

//        Ex7*: Поворот экрана
        if(driver.getOrientation() != ScreenOrientation.PORTRAIT){
            driver.rotate(ScreenOrientation.PORTRAIT);
        }

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }


//    Ex5: Тест: сохранение двух статей
    @Test
    public void saveTwoArticleToMyList() {

        String first_article_title = "Java";
        String first_article_title_full = "Java (programming language)";
        String second_article_title = "C#";
        String second_article_title_full = "C Sharp (programming language)";
        String name_of_folder = "Learning programming";


        searchArticlesAndOpenOne(first_article_title, first_article_title_full);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/title'][@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5);

        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.LinearLayout']//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        searchArticlesAndOpenOne(second_article_title, second_article_title_full);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/title'][@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/title'][@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created reading list " + name_of_folder,
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My List",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder " + name_of_folder,
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='" + first_article_title_full + "']"),
                "Cannot find saved article " + first_article_title_full,
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='" + first_article_title_full + "']"),
                "Cannot find saved article " + first_article_title_full
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='" + first_article_title_full + "']"),
                "Cannot delete saved article " + first_article_title_full,
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/reading_list_contents']//*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request",
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "The number of expected articles does not match",
                amount_of_search_results == 1
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + second_article_title_full + "')]"),
                "Cannot find saved article " + second_article_title_full,
                5
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//*[contains(@text, '" + second_article_title_full + "')]"),
                "There is no search field on the screen",
                5
        );

        Assert.assertEquals("Title does not match",
                second_article_title_full,
                title_element.getText()
        );
    }


//    Ex6: Тест: assert title
    @Test
    public void checkArticleTitleWithoutWaiting(){

        String first_article_title = "Java";
        String first_article_title_full = "Java (programming language)";

        searchArticle(first_article_title);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + first_article_title_full + "']"),
                "Cannot find article " + first_article_title_full,
                5
        );

        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title");
    }



    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    private WebElement waitForElementPresent(By by, String error_message) {

        return waitForElementPresent(by, error_message, 5);

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        driver.hideKeyboard();
        return element;
    }


    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


    protected void swipeUP(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUPQuick() {
        swipeUP(200);
    }

    protected void swipeUPToFindElement(By by, String error_message, int max_swipes) {

        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if(already_swiped > max_swipes){
                waitForElementPresent(
                        by,
                        "Cannot find element by swiping up. \n" + error_message,
                        0);
                return;
            }
            swipeUPQuick();
            ++already_swiped;
        }
    }


    protected void swipeElementToLeft(By by,String error_message) {

       WebElement element = waitForElementPresent(
               by,
               error_message,
               10);

       int left_x = element.getLocation().getX();
       int right_x = left_x + element.getSize().getWidth();
       int upper_y = element.getLocation().getY();
       int lower_y = upper_y + element.getSize().getHeight();
       int middle_y = (upper_y + lower_y) / 2;


       TouchAction action = new TouchAction(driver);
       action
               .press(right_x, middle_y)
               .waitAction(300)
               .moveTo(left_x, middle_y)
               .release()
               .perform();
    }

    private int getAmountOfElements(By by) {

        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message) {

        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }


    private void assertElementPresent(By by, String error_message){

        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements == 0) {
            String default_message = "An element " + by.toString() + " supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private void searchArticle(String search_text) {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia input'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_text,
                "Cannot find search input",
                5
        );
    }

    private void searchArticlesAndOpenOne(String search_text, String article_title) {

        searchArticle(search_text);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article_title + "']"),
                "Cannot find article " + article_title,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
    }

}
