package com.qa.writing;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class WritingMyNameTest {
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
		//driver.quit();
	}
	
	@Test
	public void writeMyName()
	{
		Actions write = new Actions(driver);
		driver.manage().window().maximize();
		driver.get("https://www.youidraw.com/apps/painter/#");

		Action moveToStart = write.moveByOffset(800, 550).build();
		moveToStart.perform();
		write.pause(1500); // wait for page to load
		
		Action writeM = write.clickAndHold().moveByOffset(0, -160).moveByOffset(40,40).moveByOffset(40, -40).moveByOffset(0, 160).release().build();
		writeM.perform();
		
		Action space = write.moveByOffset(60, 0).build();
		space.perform();
		
		Action writeI = write.clickAndHold().moveByOffset(0, -160).moveByOffset(0, 160).release().build();
		writeI.perform();
		
		space.perform();
		
		Action writeK = write.clickAndHold().moveByOffset(0, -160).moveByOffset(0, 80).moveByOffset(80, 80).moveByOffset(-80, -80).moveByOffset(80, -80).moveByOffset(-80, 80).moveByOffset(0, 80).release().moveByOffset(40,0).build();
		writeK.perform();
		
		space.perform();
		
		Action writeE = write.clickAndHold().moveByOffset(0, -160).moveByOffset(60,0).moveByOffset(-60, 0).moveByOffset(0, 80).moveByOffset(60, 0).moveByOffset(-60, 0).moveByOffset(0, 80).moveByOffset(60, 0).moveByOffset(-60, 0).release().build();
		writeE.perform();

	}
}
