package com.yash.Training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import  org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class NewWorld {

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

    @Test(groups = {"demo"}, enabled = true)
    @Parameters({"browser"})
    public void Yash(String browser, String FirstName) {
        System.out.println(browser + FirstName);
        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test(groups = {"bemo"}, enabled = true)
    public void Bush() {
        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test(dependsOnGroups = {"demo", "bemo"})
    public void Rohi() {
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

