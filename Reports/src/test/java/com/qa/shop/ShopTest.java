package com.qa.shop;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ShopTest {

		public WebDriver driver = null;
		static ExtentReports extent;
		ExtentTest test;
		
		@BeforeClass
		public static void startup() {
			extent = new ExtentReports("C:\\Users\\Admin\\Desktop\\Reports\\ShopSiteTest\\DressSearchTest.html", true);
		}
		@Before
		public void setup() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		@After
		public void teardown() {
			extent.endTest(test);
			driver.quit();
		}
		@AfterClass
		public static void end() {
			extent.flush();
		}
		
		@Test
		public void testSearch() {
			test = extent.startTest("Testing whether a specific dress is found after searching for 'Dress' term");
			
			// Open web-site
			test.log(LogStatus.INFO, "Opening website..");
			driver.get("http://automationpractice.com/index.php");
			
			WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
			WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button"));
			
			// Perform search
			test.log(LogStatus.INFO, "Entering 'Dress' to Search-bar..");
			searchBox.sendKeys("Dress");
			searchButton.click();
			WebElement specificDress = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img"));
			
			// Perform test
			assertEquals("Printed Summer Dress", specificDress.getAttribute("title"));
			
			// Log test
			if(specificDress.getAttribute("title").equals("Printed Summer Dress")) {
				test.log(LogStatus.PASS, "Test passed!");
			}
			else {
				test.log(LogStatus.FAIL, "Test failed.");
			}
			
			// Test complete
			test.log(LogStatus.INFO, "Test complete");
		}
}
