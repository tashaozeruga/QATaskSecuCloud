package com.tasha.md5checker;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by nataliiayakymenko on 29.06.17.
 */
class Md5CheckerTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private String absolutefilepath = "~/QA_Test/Files/Test_QA.pdf";
    private String md5FingerprintActual = "ef9ecb289838bf9ff5a2fac936fc6b52";
    private static String md5FingerprintTest;

    @Before
    public void start() {

        //FirefoxDriverManager.getInstance().setup();
        ChromeDriverManager.getInstance().setup();
        //InternetExplorerDriverManager.getInstance().setup();

        //driver = new InternetExplorerDriver();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("localhost:8080");
        //driver.findElement(By.name("q")).sendKeys("automated testing");
        wait.until(ExpectedConditions.titleIs("Checksum It!"));
    }

    //List<String> testFiles = new List<String>();
    //testFiles.add("filepath");
    //testFiles.add("filepath");

    public static String md5CheckSumCalc (String absolutefilepath) {
        // do something
        driver.findElement(By.name("upload")).sendKeys(absolutefilepath);
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));

        return driver.findElement(By.tagName("td")).getText();
    }

    @Test
    public void MyTest() {


        //driver.findElement(By.name("upload")).sendKeys("/home/tasha/Desktop/QA_Test/Files/Test_QA.pdf");
        //driver.findElement(By.tagName("button")).click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
        //md5FingerprintTest = driver.findElement(By.tagName("td")).getText();
        Assert.assertTrue(md5FingerprintActual.equals(
                md5CheckSumCalc("/home/tasha/Desktop/QA_Test/Files/Test_QA.pdf")));

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue("Md5sum fingerprint is incorrect", md5FingerprintActual.equals(md5FingerprintTest));
        driver.findElement(By.tagName("a")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
