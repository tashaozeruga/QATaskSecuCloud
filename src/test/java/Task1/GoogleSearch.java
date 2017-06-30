package Task1;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Travel-user on 3/27/2017.
 */
public class GoogleSearch {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        //FirefoxDriverManager.getInstance().setup();
        ChromeDriverManager.getInstance().setup();
        //InternetExplorerDriverManager.getInstance().setup();

        //driver = new InternetExplorerDriver();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void MyTest() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("automated testing");
        driver.findElement(By.name("btnG")).click();
        wait.until(ExpectedConditions.titleIs("Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

