package com.yash.Training;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import  org.testng.annotations.*;
import com.yash.util.DataFileReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelloWorld {

    WebDriver driver;


    @BeforeClass(alwaysRun = true)
    public void whatHappensBeforeClass() throws IOException {
        System.out.println("Before Class");
        System.setProperty("webdriver.chrome.driver", "/Users/yperli/Downloads/chromedriver 2");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
    }

    @BeforeTest(alwaysRun = true)
    public void whatHappensBeforeTest() {
        System.out.println("Before Test");
    }

    @BeforeMethod(alwaysRun = true)
    public void whatHappensBeforeMethod() {
        System.out.println("Before Method");
    }

    @Test(groups = { "demo" })
    public void Yash() {
        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test(groups = { "bemo", "remo" })
    public void Rohi() {
        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test(groups = { "bemo" })
    public void Prohi() {
        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
    }

    @AfterMethod(alwaysRun = true)
    public void whatHappensAfterMethod() {
        System.out.println("After Method");
    }

    @AfterTest(alwaysRun = true)
    public void whatHappensAfterTest() {
        System.out.println("After Test");
    }

    @AfterClass(alwaysRun = true)
    public void voidWhatHappensAfterClass(){
        System.out.println("After Class");
        driver.quit();
    }
}
