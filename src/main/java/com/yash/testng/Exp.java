package com.yash.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Exp {

    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void whatHappensBeforeClass(){
        System.out.println("Before Class");
        System.setProperty("webdriver.chrome.driver", "/Users/yperli/Downloads/chromedriver 2");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.get("https://demoqa.com/text-box");
    }

    @Test(enabled = false)
    public void DemoQATestBoxPage() throws InterruptedException {

        //Assert Title of The Page
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "ToolsQA");

        //Assert fullName field text and Enter value
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement fnameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='userName-label']")));
        String fnameFieldText = fnameField.getText();
        Assert.assertEquals(fnameFieldText, "Full Name");
        WebElement fullNameTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userName']")));
        fullNameTextField.sendKeys("yperli");
//        String fullNameField = getText("//label[@id='userName-label']", 60);
//        Assert.assertEquals(fullNameField, "Full Name");
//        sendKeys("//input[@id='userName']", 60, "yperli");
//
//        //Assert Email field text and Enter value
//        String emailField = getText("//label[@id='userEmail-label']", 60);
//        Assert.assertEquals(emailField, "Email");
//        sendKeys("//input[@id='userEmail']", 60, "yperli@cisco.com");
//
//        //Assert current Address field text and Enter value
//        String presentAddressField = getText("//label[@id='currentAddress-label']", 60);
//        Assert.assertEquals(presentAddressField, "Current Address");
//        sendKeys("//textarea[@id='currentAddress']", 60, "Vijayawada-Labbipet");
//
//        //Assert Permanent Address field text and Enter value
//        String PermanentAddressField = getText("//label[@id='permanentAddress-label']", 60);
//        Assert.assertEquals(PermanentAddressField, "Permanent Address");
//        sendKeys("//textarea[@id='permanentAddress']", 60, "Vijayawada-Gunadala");
//
//        //Click on Submit Button
//        click("//button[@id='submit']", 60);
        Thread.sleep(10000);
    }
}
