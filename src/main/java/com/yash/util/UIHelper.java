package com.yash.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIHelper {

    public void click(WebDriver driver,  String xpath, int waitTime) throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        WebElement element = getElementBy(driver, xpath, waitTime);
        element.click();
    }

    public void sendKeys(WebDriver driver, String xpath, int waitTime, String value) throws InterruptedException {
        Thread.sleep(1000);
        WebElement element = getElementBy(driver,xpath, waitTime);
        element.sendKeys(value);
    }

    public String getText(WebDriver driver, String xpath, int waitTime) throws InterruptedException {
        Thread.sleep(1000);
        WebElement element = getElementBy(driver, xpath, waitTime);
        return element.getText();
    }

    public WebElement getElementBy(WebDriver driver, String xpath, int waitTime) throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }
}
