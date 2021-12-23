package com.yash.main;

import com.yash.util.DataFileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class InitialSuiteSetup {

    public WebDriver driver;
    DataFileReader d = new DataFileReader();
    @BeforeClass
    @Parameters({"browser"})
    public void initialBrowserSetup(String browser) throws IOException, ParseException {
        String user_dir = System.getProperty("user.dir");
        JSONObject j = d.getJson(user_dir + "/src/test/resources/test_config.json");
        System.out.println(j.get("docker_run"));
        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", user_dir + "/chromedriver 2");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
        } else if(browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", user_dir + "/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            //options.setHeadless(true);
            options.addArguments("--window-size").addArguments("1920,1080");
            driver = new FirefoxDriver(options);
        }

    }
}
