package com.qa.cucumber;

import static org.junit.Assert.assertEquals;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;


public class ShoppingStep {
	
	WebDriver driver;
	static ExtentReports report = new ExtentReports("C:\\Users\\Admin\\Desktop\\Reports\\TeaSiteTest\\TeaSiteReport.html", true);
	ExtentTest test = null;
	public static int count = 0;

	@Before
	public void setup() {
	}
	@After
	public void teardown() {
	}

	
	@Given("^the correct web address$")
	public void the_correct_web_address() {
		if(count == 0)
		{
			test = report.startTest("Browsing Items");
		}
		else
		{
			test = report.startTest("Checkouts");
		}
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Starting Test~ request website");
		test.log(LogStatus.INFO, "Requesting website..");
	    driver.get("http://www.practiceselenium.com/welcome.html");
		LandingPage_POM landingPage = PageFactory.initElements(driver, LandingPage_POM.class);
		test.log(LogStatus.INFO, "Asserting correct page..");
		
		try {
	    assertEquals("We're passionate about tea. ", landingPage.header.getText());
		}
		catch (Exception e)
		{
			test.log(LogStatus.FAIL, "Could not access correct website.");
		}
		test.log(LogStatus.PASS, "Successfully navigated to website!");
	}
	
	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {
		test.log(LogStatus.INFO, "Starting Test~ navigate to menu page");
		test.log(LogStatus.INFO, "Clicking menu button..");
		LandingPage_POM landingPage = PageFactory.initElements(driver, LandingPage_POM.class);
		landingPage.mainMenuButton.click();
		TeaPage_POM teaPage = PageFactory.initElements(driver, TeaPage_POM.class);
		
		test.log(LogStatus.INFO, "Asserting correct page..");
		try {
			assertEquals("Menu", teaPage.title.getText());
		}
		catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to navigate to menu page.");
		}
		test.log(LogStatus.PASS, "Successfully navigated to menu page!");
	}
	
	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {
		TeaPage_POM teaPage = PageFactory.initElements(driver, TeaPage_POM.class);
		test.log(LogStatus.INFO, "Starting Test~ browse list of products");
		// Products
		try {
			assertEquals(true, teaPage.tea.getText().contains("Tea"));			
		}
		catch (Exception e) {
			test.log(LogStatus.FAIL, "Product 1 not found.");
		}
		test.log(LogStatus.PASS, "Successfully loaded products.");
		report.endTest(test);
		driver.quit();
		count++;
	}
	
	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
		LandingPage_POM landingPage = PageFactory.initElements(driver, LandingPage_POM.class);
		test.log(LogStatus.INFO, "Starting Test~ checkout buttons");
		
		test.log(LogStatus.INFO, "Navigating to menu of products..");
		landingPage.mainMenuButton.click();
		
		TeaPage_POM teaPage = PageFactory.initElements(driver, TeaPage_POM.class);
		test.log(LogStatus.INFO, "Clicking buttons..");
		
		try { teaPage.checkoutB1.click(); } catch(Exception e) { 		test.log(LogStatus.FAIL, "Button 1 failed"); }
		driver.navigate().back();
		
		try { teaPage.checkoutB2.click(); } catch(Exception e) { 		test.log(LogStatus.FAIL, "Button 2 failed"); }
		driver.navigate().back();
		try { teaPage.checkoutB3.click(); } catch(Exception e) { 		test.log(LogStatus.FAIL, "Button 3 failed"); }
		
		test.log(LogStatus.PASS, "Successfully clicked checkout buttons.");
	}
	
	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
		PaymentPage_POM paymentPage = PageFactory.initElements(driver,  PaymentPage_POM.class);
		
		test.log(LogStatus.INFO, "Starting Test~ checkout page");
		
		test.log(LogStatus.INFO, "Asserting we're on the correct page..");
		try { assertEquals("Enter your billing information", paymentPage.getPayText()); } catch (Exception e) {		test.log(LogStatus.FAIL, "Incorrect page."); }
		
		test.log(LogStatus.PASS, "Successfully reached the checkout page.");
		report.endTest(test);
		report.close();
		report.flush();
		driver.close();
	}
}
