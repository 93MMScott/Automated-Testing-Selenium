package com.qa;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateAndLoginTest {

	public static WebDriver driver = null;
	static ExtentReports extent;
	ExtentTest test;
	static XSSFWorkbook workbook;

	@BeforeClass
	public static void startup()
	{
		extent = new ExtentReports("C:\\Users\\Admin\\Desktop\\Reports\\DemoSiteTest\\LoginTest.html", true);
	}
	
	@Before
	public void setup() {
		ExcelUtils.setExcelSheet("C:\\Users\\Admin\\Downloads\\DemoSiteDDT.xlsx", 0);
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After
	public void teardown() {
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
	
	@AfterClass
	public static void end() throws IOException {
		workbook.close();
	}
	
	@Test
	public void CreateAndLoginTesting() throws IOException, InterruptedException {
		FileInputStream file = new FileInputStream("C:\\Users\\Admin\\Downloads\\DemoSiteDDT.xlsx");
		workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		
		for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
		{
			
			//Our login data
			Cell user = sheet.getRow(i).getCell(0);
			Cell pass = sheet.getRow(i).getCell(1);
			
			String username = user.getStringCellValue();
			String password = pass.getStringCellValue();
			
			//Test = Create a User and test Login
			test = extent.startTest("Testing website user creation and login on thedemosite.co.uk");
			
			driver.get("http://thedemosite.co.uk/");
	
			// Create user
			test.log(LogStatus.INFO, "Creating the user..");
			
			WebElement addUserButton = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]"));
			addUserButton.click();
			WebElement username_addBox = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
			WebElement password_addBox = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
			username_addBox.sendKeys(username);
			password_addBox.sendKeys(password);
			WebElement finalAddUserButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
			finalAddUserButton.click();
			
	
			// Login
			test.log(LogStatus.INFO, "Attempting login process..");
			
			WebElement loginButton = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
			loginButton.click();
			WebElement usernameBox = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
			WebElement passwordBox = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
			usernameBox.click();
			usernameBox.sendKeys(username);
			passwordBox.click();
			passwordBox.sendKeys(password);
			WebElement finalLoginButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
			finalLoginButton.click();
			
			// Check that the text says login successful
			WebElement text = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
			
			if(text.getText().equals("**Successful Login**"))
			{
				test.log(LogStatus.PASS, "Successfully logged in!");
				ExcelUtils.setCellData("Passed", i, 4);
			}
			else
			{
				test.log(LogStatus.FAIL, "Failed to log in.");
				ExcelUtils.setCellData("Fail", i, 4);
			}
			assertEquals("**Successful Login**", text.getText());
			test.log(LogStatus.INFO, "Test complete.");
		}
	}
}
