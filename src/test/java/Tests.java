import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Tests {
    private static WebDriver driver = WebDriverBase.getDriver();

    @BeforeClass
    public static void openSite() {
        driver.get(Constants.URL);
        driver.manage().window().maximize();
    }

    @Test
    public void implicitWaitMethod() {
        driver.manage().timeouts().implicitlyWait(Constants.DELAY, TimeUnit.SECONDS);
        driver.get(Constants.ORDER_PAGE);
        Assert.assertNotNull(driver.findElement(By.xpath(Constants.YOUR_SHOPPING_CART_LABEL)));
    }

    @Test
    public void explicitWaitMethod() {
        driver.get(Constants.ORDER_PAGE);
        Assert.assertNotNull(new WebDriverWait(driver, Constants.DELAY).withTimeout(Duration.ofSeconds(Constants.DELAY))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(Constants.YOUR_SHOPPING_CART_LABEL))));
    }

    @Test
    public void fluentWaitMethod() {
        driver.get(Constants.ORDER_PAGE);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Constants.DELAY))
                .pollingEvery(Duration.ofMillis(Constants.MS_DELAY))
                .ignoring(NoSuchElementException.class);
        Assert.assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Constants.YOUR_SHOPPING_CART_LABEL))));
    }

    @AfterClass
    public static void closeDriver() {
        driver.quit();
    }

}
