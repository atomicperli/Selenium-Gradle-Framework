package com.yash.main;

import com.yash.util.DataFileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;

public class InitialSuiteSetup extends Start{

    public WebDriver driver;
    DataFileReader d = new DataFileReader();
    @BeforeClass
    @Parameters({"browser"})
    public void initialBrowserSetup(String browser) throws IOException, ParseException {
        try {
            String docker_run = DOCKER_RUN;
            if (docker_run.equalsIgnoreCase("true")) {
                JSONObject j = d.getJson("/tmp/test_config.json");
                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless");
                    options.addArguments("--window-size=1920,1080");
                    driver = new RemoteWebDriver(new URL("http://chrome:4444/wd/hub"), options);
                } else if (browser.equalsIgnoreCase("firefox")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(true);
                    options.addArguments("--window-size").addArguments("1920,1080");
                    driver = new RemoteWebDriver(new URL("http://firefox:4444/wd/hub"), options);
                }
            } else {
                String user_dir = System.getProperty("user.dir");
                JSONObject j = d.getJson(user_dir + "/src/test/resources/test_config.json");
                System.setProperty("webdriver.chrome.driver", user_dir + "/chromedriver 2");
                System.setProperty("webdriver.gecko.driver", user_dir + "/geckodriver");
                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless");
                    options.addArguments("--window-size=1920,1080");
                    driver = new ChromeDriver(options);
                } else if (browser.equalsIgnoreCase("firefox")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--window-size").addArguments("1920,1080");
                    driver = new FirefoxDriver(options);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
