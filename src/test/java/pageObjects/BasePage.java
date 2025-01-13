package pageObjects;
//Base Page - Consists of all the repeatative methods or simply we can call utility page
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
/*
 * This Constructor and class variable is common to all page object classes
 * Hence the constructor and class variable is specified in separate BasPage class
 * And this parent class can be inherited by child classes
 */

public class BasePage 
{
	//class variable
	WebDriver driver;

	//constructor
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
