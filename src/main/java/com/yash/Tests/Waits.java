package com.yash.Tests;

import com.yash.main.InitialSuiteSetup;
import com.yash.util.DataFileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;

import com.yash.util.UIHelper;

public class Waits extends InitialSuiteSetup {

    UIHelper uiHelper = new UIHelper();
    JSONObject JsonTestData;
    DataFileReader d = new DataFileReader();

    @BeforeClass(alwaysRun = true)
    public void whatHappensBeforeClass() {
        try {
            System.out.println("Before Class");
            String testName = getClass().getSimpleName();
            String filename = null;
            String commonFileName = null;
            if(DOCKER_RUN.equalsIgnoreCase("true")) {
                filename = "/tmp/" + testName + "/Waits.json";
                commonFileName = "/tmp/common_config.json";
            } else {
                String user_dir = System.getProperty("user.dir");
                filename = user_dir + "/src/test/resources/" + testName + "/Waits.json";
                commonFileName = user_dir + "/src/test/resources/common_config.json";

            }
            JSONObject[] jsonFiles = {d.getJson(filename), d.getJson(commonFileName)};
            JsonTestData = d.mergeJsonFiles(jsonFiles);
            driver.manage().window().maximize();
            driver.get("https://demoqa.com/text-box");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void DemoQATestBoxPage() throws InterruptedException {

        //Assert Title of The Page
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "ToolsQA");

        //Assert fullName field text and Enter value
        String fullNameField = uiHelper.getText(driver, (String) JsonTestData.get("fullNameTextBoxField"), 60);
        Assert.assertEquals(fullNameField, (String) JsonTestData.get("fullNameText"));
        uiHelper.sendKeys
                (driver, (String) JsonTestData.get("fullNameTextBox"), 60, (String) JsonTestData.get("fullNameValue"));

        //Assert Email field text and Enter value
        String emailField = uiHelper.getText(driver,(String) JsonTestData.get("emailTextBoxField"), 60);
        Assert.assertEquals(emailField, (String) JsonTestData.get("emailText"));
        uiHelper.sendKeys
                (driver, (String) JsonTestData.get("emailTextBox"), 60, (String) JsonTestData.get("emailValue"));

        //Assert current Address field text and Enter value
        String presentAddressField = uiHelper.getText(driver,(String) JsonTestData.get("presentAddressTextBoxField"), 60);
        Assert.assertEquals(presentAddressField, (String) JsonTestData.get("presentAddressText"));
        uiHelper.sendKeys
                (driver,(String) JsonTestData.get("presentAddressTextBox"), 60, (String) JsonTestData.get("presentAddress"));

        //Assert Permanent Address field text and Enter value
        String PermanentAddressField = uiHelper.getText(driver, (String) JsonTestData.get("permanentAddressTextBoxField"), 60);
        Assert.assertEquals(PermanentAddressField, (String) JsonTestData.get("permanentAddressText"));
        uiHelper.sendKeys
                (driver,(String) JsonTestData.get("permanentAddressTextBox"), 60, (String) JsonTestData.get("permanentAddress"));

        //Click on Submit Button
        uiHelper.click(driver,(String) JsonTestData.get("submitButton"), 60);
    }

    @Test(enabled = false)
    public void DemoQARadioButtonPage() throws InterruptedException{
        driver.navigate().to("https://demoqa.com/radio-button");
        List<WebElement> radioButtons= driver.findElements(By.xpath("//input[@id='yesRadio']"));
//        System.out.println(radioButtons.size());
//        click("//input[@id='yesRadio']", 60);
//        WebElement yesRadioButton = getElementBy("//input[@id='yesRadio']", 60);
//        Assert.assertTrue(yesRadioButton.isSelected());
        WebElement noRadioButton = getElementBy("//input[@id='noRadio']", 60);
        Assert.assertFalse(noRadioButton.isEnabled());
    }

    //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))); // waits until element is visible
    //WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))); // waits until element's presence is detected in the DOM
    //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))); // waits until element is Clickable
    //wait.until(ExpectedConditions.titleIs("Some String")); // waits Until the Title of the page is what we want
    //wait.until(ExpectedConditions.urlContains("A part of Url")); // waits Until the url contains the the text we want
    //wait.until(ExpectedConditions.urlToBe("URL"));// com.yash.Tests.Waits until the URL is what we want
    //wait.until(ExpectedConditions.attributeToBe(By.id("id"), "name", "yash"));// com.yash.Tests.Waits until the attribute of an element is what we want

    @Test(enabled = false)
    public void TestLinksPage() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/links");
        getElementBy("//div[@class='main-header' and text()='Links']", 60);
        driver.findElements(By.tagName("a")); // gets all Elements in a page with tag 'a' ('a' tag represents Links)
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for(WebElement e: links)
        {
            System.out.println(e.getAttribute("href"));// get Attribute function returns the value of a particular attribute of a tag
        }
    }

    @Test(enabled = false)
    public  void testTabsAndWindows() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/browser-windows");
        click("//button[@id='windowButton']", 60);
        String presentWindow = driver.getWindowHandle(); //getWindowHandle() returns the Address of the current browser Window
        Set<String> windows = driver.getWindowHandles(); //getWindowHandles() returns the Address of all Windows that are open
        int count = 1;
        for(String window: windows) {
            System.out.println(count);
            if (count == 2) {
                driver.switchTo().window(window); // Switches to a particular window
                System.out.println("switched window");
                Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/sample"); // getCurrentUrl() function returns the URL on the page
                driver.close();
            } else {
                count = count + 1;
                continue;
            }

        }
        driver.switchTo().window(presentWindow);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/browser-windows");
    }

    @Test(enabled = false)
    public void alertHandling() throws InterruptedException {

        //driver.switchTo().alert().accept() --> Clicks on Ok Button of the alert
        // driver.switchTo().alert().dismiss() --> Clicks on Cancel Button of the alert
        // driver.switchTo().alert().getText() --> Retrieves the text on the Alert
        // driver.switchTo().alert().sendKeys("<string>") --> Sends value into a textbox inside the alert

        driver.navigate().to("https://demoqa.com/alerts");
        driver.navigate().refresh();
        click("//button[@id = 'promtButton']", 60);
        Assert.assertEquals(driver.switchTo().alert().getText(), "Please enter your name");
        driver.switchTo().alert().sendKeys("Prohi");
        Thread.sleep(1000); //Adding sleep to see the execution a bit slowly
        driver.switchTo().alert().accept();

        click("//button[@id = 'promtButton']", 60);
        Assert.assertEquals(driver.switchTo().alert().getText(), "Please enter your name");
        driver.switchTo().alert().sendKeys("Prohi");
        Thread.sleep(1000); //Adding sleep to see the execution a bit slowly
        driver.switchTo().alert().dismiss();
    }

    @Test(enabled = false)
    public void HandingSelectDropdowns() throws InterruptedException {
        driver.navigate().to("https://formstone.it/components/dropdown/demo/");

        //Selecting a value in single selecr dropdown
        Select dropdown1 = new Select(getElementBy("//select[@id='demo_basic']", 60)); // Using Select class to get a dropdown Element
        dropdown1.selectByIndex(0); // selects the value in the dropdown based on the index we give
        dropdown1.selectByVisibleText("One"); // selects the value in the dropdown based on the Text
        dropdown1.selectByValue("1"); // selects the value in the dropdown based on the value attribute of the option tag

        WebElement disabledDropDown = getElementBy("//select[@id='demo_disabled']", 60);
        Assert.assertFalse(disabledDropDown.isEnabled()); //isEnabled() function asserts if the element is enabled or not

        WebElement multiSelectDropDown = getElementBy("//div[@id='demo_multiple']", 60);

        //Scrolling to a Element in a WebPage
        JavascriptExecutor js = (JavascriptExecutor) driver; // We need to cast our WebDriver to JavascriptExecutor Class
        js.executeScript("arguments[0].scrollIntoView();", multiSelectDropDown); // scrolls to a particular element

        // Selecting Multiple Elements from a Multi Select Dropdown

        Select mSelect = new Select(getElementBy("//div[@id='demo_multiple']", 60));
        mSelect.selectByVisibleText("Two");
        mSelect.selectByVisibleText("Four");
        mSelect.selectByVisibleText("Five");

        Thread.sleep(1000);

        mSelect.deselectByIndex(1); // deselects the value in the dropdown based on the index we give
        mSelect.deselectByValue("4"); // deselects the value in the dropdown based on the value attribute of the option tag
        mSelect.deselectByVisibleText("Five"); // deselects the value in the dropdown based on the Text

        mSelect.selectByVisibleText("Two");
        mSelect.selectByVisibleText("Four");
        mSelect.selectByVisibleText("Five");

        Thread.sleep(1000);

        mSelect.deselectAll(); // deselects all the Selected Options in a multi select dropdown

        Thread.sleep(1000);

    }

    @Test(enabled = false)
    public void clicks() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/tool-tips");
        Actions action = new  Actions(driver);
        WebElement hoverButton = getElementBy("//button[@id='toolTipButton']", 60);
        action.moveToElement(hoverButton).perform(); // hovering on a element
        action.contextClick(hoverButton).perform(); // right click on a element
        action.doubleClick(hoverButton).perform(); // double click on a element

        // Using build function to chain Multiple Actions into one
        Action seriesOfActions;
        seriesOfActions = action.moveToElement(hoverButton).contextClick(hoverButton).doubleClick(hoverButton).build();
        seriesOfActions.perform();
        //Assert.assertTrue(false);
    }

    @Test(enabled = false)
    public void FrameFunctions() {

        // Frames are elements with iframe tags
        //driver.switchTo().frame(WebElement); switch to a particular frame
        //driver.switchTo().parentFrame(); switch to Parent frame
        //driver.switchTo().defaultContent(); switch to Parent frame

    }

    public void click(String xpath, int waitTime) throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        WebElement element = getElementBy(xpath, waitTime);
        element.click();
    }

    public void sendKeys(String xpath, int waitTime, String value) throws InterruptedException {
        Thread.sleep(1000);
        WebElement element = getElementBy(xpath, waitTime);
        element.sendKeys(value);
    }

    public String getText(String xpath, int waitTime) throws InterruptedException {
        Thread.sleep(1000);
        WebElement element = getElementBy(xpath, waitTime);
        return element.getText();
    }

    public WebElement getElementBy(String xpath, int waitTime) throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    //@AfterMethod
    public void TakeScreenShot(ITestResult result) throws IOException {
        //Initialize Screenshot Class Object by casting the Driver to TakesScreenshot class
        if(result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);

            //Call getScreenshotAs method to create image file

            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

            File DestFile = new File("/Users/yperli/Selenium-Screenshots/hi.png");

            //Copy file at destination

            Files.copy(SrcFile.toPath(), DestFile.toPath());
        }

    }



    @AfterClass
    public void quit(){
        driver.quit();
    }

}
