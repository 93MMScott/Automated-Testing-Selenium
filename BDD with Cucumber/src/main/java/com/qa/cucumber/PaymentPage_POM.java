package com.qa.cucumber;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage_POM {
	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000451990420\"]/div/pre/strong/span")
	private WebElement paymentBlurb;
	
	public String getPayText() {
		return paymentBlurb.getText();
	}
}
