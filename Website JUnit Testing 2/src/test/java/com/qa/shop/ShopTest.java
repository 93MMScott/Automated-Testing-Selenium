package com.qa.shop;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShopTest {

		public WebDriver driver = null;
		
		@Before
		public void setup() 
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		@After
		public void teardown() 
		{
			driver.quit();
		}
		
		@Test
		public void testSearch()
		{
			driver.get("http://automationpractice.com/index.php");
			WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"search_query_top\"]"));
			WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button"));
			searchBox.sendKeys("Dress");
			searchButton.click();
			WebElement specificDress = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img"));
			assertEquals("Printed Summer Dress", specificDress.getAttribute("title"));
		}
}
