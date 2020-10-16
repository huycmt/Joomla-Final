package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

import static utilities.Constant.TIME_OUT_SHORT;
import static utilities.Log.info;

public class DriverHelper {
    static WebDriver driver;
    static WebDriverWait wait;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        wait = new WebDriverWait(driver, TIME_OUT_SHORT);
        wait.until(pageLoadCondition);
    }

    public static String getNewWindowUrl() {
        //Store the main window
        String mainWindow = getDriver().getWindowHandle();

        // Get all Opened Window
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!mainWindow.equals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                waitForLoad();
                return getDriver().getCurrentUrl().trim();
            }
        }
        return "";
    }

    public static void scrollToView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void waitUntilElementContainsText(WebElement element, String text) {
        wait = new WebDriverWait(getDriver(), TIME_OUT_SHORT);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitUntilElementClickable(WebElement element) {
        wait = new WebDriverWait(getDriver(), TIME_OUT_SHORT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementVisibility(WebElement element) {
        wait = new WebDriverWait(getDriver(), TIME_OUT_SHORT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void startBrowser(String browser) {
        info("-----------------------Start browser------------------------\n");
        switch (browserType.valueOf(browser)) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();
    }

    public void navigateTo(String url) {
        info("Navigate to url: " + url);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(TIME_OUT_SHORT, TimeUnit.SECONDS);
    }

    public void quitDriver() {
        if (null != driver) {
            info("-----------------------Close browser------------------------\n\n");
            driver.quit();
            driver = null;
        }
    }

    public enum browserType {
        CHROME, FIREFOX, EDGE
    }
}
