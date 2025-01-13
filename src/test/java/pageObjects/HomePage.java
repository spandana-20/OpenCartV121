package pageObjects;
//Home Page Object class

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	//Constructor
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(css="li[class='dropdown open'] li:nth-child(2) a:nth-child(1)")
	WebElement lnklogin;
	
	//Actions
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	public void ClickRegister()
	{
		lnkRegister.click();
	}
	
	public void ClickLogin()
	{
		lnklogin.click();
	}
}
