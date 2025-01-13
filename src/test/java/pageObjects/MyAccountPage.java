package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
	//Constructor
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locator 
	@FindBy(xpath = "//h2[normalize-space()='My Account']") //My Account Page heading 
	WebElement msgHeading;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']") //Added as per step 6
	WebElement lnklogout;
	
	//Action Methods
	public boolean isMyAccountPageExists()
	{
		try 
		{
			return (msgHeading.isDisplayed());
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public void clicklogout() 
	{
		lnklogout.click();
	}
	
}
