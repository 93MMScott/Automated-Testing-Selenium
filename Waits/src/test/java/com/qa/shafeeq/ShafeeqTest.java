package com.qa.shafeeq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ShafeeqTest {
	
	public WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void test() {
		driver.get("https://chrisperrins95.github.io/AutomatedTestingExample/");
		(new WebDriverWait(driver, 8)).until(ExpectedConditions.presenceOfElementLocated(By.id("shafeeq")));
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}