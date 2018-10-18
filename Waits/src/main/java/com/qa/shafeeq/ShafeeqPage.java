package com.qa.shafeeq;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShafeeqPage {
	@FindBy(id = "shafeeq")
	private WebElement shafeeq;
	
	public String getShafeeqText()
	{
		return shafeeq.getText();
	}
}
