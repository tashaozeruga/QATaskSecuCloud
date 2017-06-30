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

import java.util.HashMap;

import static java.lang.Thread.sleep;

/**
 * Created by nataliiayakymenko on 30.06.17.
 */
public class MyTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private String absolutefilepath = "~/QA_Test/Files/Test_QA.pdf";
    private String md5FingerprintTest;

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

    public String md5CheckSumCalc(String absolutefilepath) {
        // do something
        driver.findElement(By.name("upload")).sendKeys(absolutefilepath);
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
        String result = driver.findElement(By.tagName("td")).getText();
        driver.findElement(By.tagName("a")).click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    public void testMd5sum() {
        String md5FingerprintActual = "ef9ecb289838bf9ff5a2fac936fc6b52";
        Assert.assertTrue("Incorrect",md5FingerprintActual.equals(
                md5CheckSumCalc("/home/nataliiayakymenko/Desktop/QA_Test/Files/Test_QA.pdf")));
    }
    @Test
    public void testMd5sumMultipleFiles() {

        HashMap<String,String> input=new HashMap<>();

        input.put("7113268f6d547bcd4a88e456f59971b1","/home/nataliiayakymenko/Desktop/QA_Test/Files/Test_QA.pdf");

        for(String actualHash: input.keySet()){
            Assert.assertTrue("Incorrect",actualHash.equals(
                    md5CheckSumCalc(input.get(actualHash))));
        }
    }

    @Test
    public void testConsistentResults() {
        String result1=md5CheckSumCalc("/home/nataliiayakymenko/Desktop/QA_Test/Files/Test_QA.pdf");
        String result2=md5CheckSumCalc("/home/nataliiayakymenko/Desktop/QA_Test/Files/Test_QA.pdf");
        Assert.assertEquals("Results not consistent",result1, result2);
    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

