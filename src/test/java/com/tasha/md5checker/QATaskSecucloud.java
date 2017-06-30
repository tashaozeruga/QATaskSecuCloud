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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.Thread.sleep;

/**
 * Created by nataliiayakymenko on 30.06.17.
 */
public class QATaskSecucloud {

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
        driver.get("localhost:8080");
        //driver.findElement(By.name("q")).sendKeys("automated testing");
        wait.until(ExpectedConditions.titleIs("Checksum It!"));
    }

    public String getCheckSumWeb (String absolutefilepath) {
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




    private String getCheckSumJava(String file) throws IOException, NoSuchAlgorithmException {
        //Use MD5 algorithm
        MessageDigest digest = MessageDigest.getInstance("MD5");

        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }


    @Test
    public void testVerifyChecksum() throws IOException, NoSuchAlgorithmException {
        String input="/home/nataliiayakymenko/Desktop/QA_Test/Files/Test_QA.pdf";
        String resultFromWeb = getCheckSumWeb(input);
        String resultJava = getCheckSumJava(input);
        Assert.assertEquals("Result is incorrect",resultFromWeb, resultJava);
    }



    @After
    public void stop() {
        driver.quit();
        driver = null;
    }



}
