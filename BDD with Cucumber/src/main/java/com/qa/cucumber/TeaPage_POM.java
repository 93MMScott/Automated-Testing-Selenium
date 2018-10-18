package com.qa.cucumber;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeaPage_POM {
	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000450914921\"]/div/h1")
	public WebElement title;
	
	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000453231735\"]/div/p/span/span/strongError")
	public WebElement tea;
	
	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000453231072\"]/div/p/span/span/strong")
	public WebElement tea2;
	
	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000453230000\"]/div/p/span/span/strong")
	public WebElement tea3;
	
	@FindBy(xpath = "//*[@id=\"wsb-button-00000000-0000-0000-0000-000451955160\"]/span")
	public WebElement checkoutB1;
	
	@FindBy(xpath = "//*[@id=\"wsb-button-00000000-0000-0000-0000-000451959280\"]/span")
	public WebElement checkoutB2;
	
	@FindBy(xpath = "//*[@id=\"wsb-button-00000000-0000-0000-0000-000451959280\"]/span")
	public WebElement checkoutB3;

}
