package Task5;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by Travel-user on 4/4/2017.
 */
public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String PAGE_URL = "http://localhost/litecart/";

    private String duckHomepageName;
    private String productPageName;

    private String priceDiscountHome;
    private String priceRegularHome;
    private String priceDiscountProductPage;
    private String priceRegularProductPage;
    private boolean discountEquals;
    private boolean regularEquals;

    WebElement regularPriceHomeFont;
    WebElement discountPriceHomeFont;
    WebElement regularPriceProductPageFont;
    WebElement discountPriceProductPageFont;
    private boolean regularPriceColorEquals;
    private boolean regularPriceDecorationEquals;
    private boolean discountPriceColorEquals;
    private boolean discountPriceWeightEquals;



    @Before
    public void start() {

        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void SeleniumTest() throws IOException {

        driver.get(PAGE_URL);
        String duckHomepageName = driver.findElement(By.cssSelector("div#box-campaigns div.name")).getText();
        priceDiscountHome = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong.campaign-price")).getText();
        priceRegularHome = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s.regular-price")).getText();

        regularPriceHomeFont = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s.regular-price"));
        discountPriceHomeFont = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong.campaign-price"));
        String regPriceColorHome = regularPriceHomeFont.getCssValue("color");
        String discPriceColorHome = discountPriceHomeFont.getCssValue("color");
        String regularPriceFontDecorationHome = regularPriceHomeFont.getCssValue("text-decoration");
        String discountPriceFontWeightHome = discountPriceHomeFont.getCssValue("font-weight");


        driver.findElement(By.cssSelector("div#box-campaigns a.link")).click();
        String code = "RD001";
        Assert.assertTrue(driver.findElement(By.cssSelector("div.codes span.sku")).getText().equals(code));



        String productPageName = driver.findElement(By.cssSelector("h1.title")).getText();
        Assert.assertTrue(duckHomepageName.equals(productPageName));

        priceDiscountProductPage = driver.findElement(By.cssSelector("div.price-wrapper strong.campaign-price")).getText();
        priceRegularProductPage =  driver.findElement(By.cssSelector("div.price-wrapper s.regular-price")).getText();
        regularPriceProductPageFont = driver.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        discountPriceProductPageFont = driver.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));

        String regPriceColorProductPage = regularPriceProductPageFont.getCssValue("color");
        String discPriceColorProductPage = discountPriceProductPageFont.getCssValue("color");
        String regularPriceFontDecorationProductPage = regularPriceProductPageFont.getCssValue("text-decoration");
        String discountPriceFontWeightProductPage = discountPriceProductPageFont.getCssValue("font-weight");

        discountEquals =  priceDiscountHome.equals(priceDiscountProductPage);
        regularEquals = priceRegularHome.equals(priceRegularProductPage);
        regularPriceColorEquals = regPriceColorHome.equals(regPriceColorProductPage);
        regularPriceDecorationEquals = regularPriceFontDecorationHome.equals(regularPriceFontDecorationProductPage);
        discountPriceColorEquals = discPriceColorHome.equals(discPriceColorProductPage);
        discountPriceWeightEquals = discountPriceFontWeightHome.equals(discountPriceFontWeightProductPage);


        Assert.assertTrue(discountEquals);
        Assert.assertTrue(regularEquals);
        Assert.assertTrue("Regular price font color on the home page does not match with product page",regularPriceColorEquals);
        Assert.assertTrue("Regular price font decoration on the home page does not match with product page", regularPriceDecorationEquals);
        Assert.assertTrue("Discount price font color on the home page does not match with product page", discountPriceColorEquals);
        Assert.assertTrue("Discount price font decoration on the home page does not match with product page", discountPriceWeightEquals);


    }



    @After
    public void stop() {
        driver.quit();
        driver = null;
    }



}