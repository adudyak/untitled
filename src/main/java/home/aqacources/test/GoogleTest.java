package home.aqacources.test;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class GoogleTest {

    // Instance of WebDriver
    private WebDriver driver;

    /**
     * Set up method
     */
    @Before
    public void setUp() {

        // If you want to disable infobars please use this code
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // Initialize path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // Initialize instance of ChromeDriver and add options
        driver = new ChromeDriver(options);

        // Set 10 second for implicitly wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Maximize window
        driver.manage().window().maximize();
    }

    /**
     * Open Google page, search and quit
     */
    @Test
    public void testOpenGoogleTest() {
        String googleSearchButton = "(//input[@class='gNO89b'])[2]";
        String googleSearchInputField = "q";
        String searchText = "Automation testing";
        String automationTestingLink = "//a[@href = 'https://www.qasymphony.com/blog/test-automation-automated-testing/']";

        // Open Google
        driver.get("https://google.com.ua");

        // Send phrase in search input
        driver.findElement(By.name(googleSearchInputField)).sendKeys(searchText);

        // Click Search
        driver.findElement(By.xpath(googleSearchButton)).click();

        // Click Automation Testing
        if (driver.findElements(By.xpath(automationTestingLink)).size() != 0) driver.findElement(By.xpath(automationTestingLink)).click();
        else TestCase.fail("Not able to find Automation Testing link");

        // Verify page
        String title = driver.findElement(By.xpath("(//title)[1]")).getText();
        Assert.assertSame("Test Automation vs. Automated Testing: The Difference Matters - QASymphony", title);
    }

    /**
     * After method, quit driver
     */
    @After
    public void tearDown() {

        // Quit from Driver. close() just close window,
        // quit() - close all window an driver
        driver.quit();
    }

}