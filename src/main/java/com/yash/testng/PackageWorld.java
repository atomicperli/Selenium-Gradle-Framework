package com.yash.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import  org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class PackageWorld {
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void whatHappensBeforeClass(String browser) throws InterruptedException{
        System.out.println("Before Class");
        if(browser.equalsIgnoreCase("chrome")) {
            System.out.println("Launching chrome");
            System.setProperty("webdriver.chrome.driver", "/Users/yperli/Downloads/chromedriver 2");
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")){
            System.out.println("Launching Firefox");
            System.setProperty("webdriver.chrome.driver", "/Users/yperli/Downloads/chromedriver 2");
            driver = new ChromeDriver();
        }
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

    @Test(priority = 1)
    public void Yash() {
        System.out.println("Yash");
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test(priority = 2)
    public void Rohi() {
        System.out.println("Rohi");
        Assert.assertEquals("Google", driver.getTitle());
//        Assert.assertEquals("Googler", "Google", "Na bondha");
////        Assert.assertNotEquals("Google", "Doogle");
////        Assert.assertNotEquals("Google", "Doogle", "Malli Na Bondha");
////        WebElement element = driver.findElement(By.id("bkka"));
////        Assert.assertTrue(element.isDisplayed());
////        Assert.assertTrue(element.isDisplayed(), "True Bondha");
////        Assert.assertFalse(element.isDisplayed());
////        Assert.assertFalse(element.isDisplayed(), "False Bondha");
        SoftAssert s = new SoftAssert();
        s.assertEquals("Googler", "Google"); //If the assert fails execution will still continue
        System.out.println("Yashu");
        s.assertAll();
    }

    @Test()
    public void pRohi() {
        System.out.println("pRohi");
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test()
    public void dRohi() {
        System.out.println("dRohi");
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
