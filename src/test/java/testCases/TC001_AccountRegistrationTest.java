package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.Baseclass;

public class TC001_AccountRegistrationTest  extends Baseclass
{
	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{
		try 
		{
			logger.info("*******Starting TC001_AccountRegistrationTest***********");
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on the Link My Account");
			
			hp.ClickRegister();
			logger.info("Clicked on the Link Register");
			
			Thread.sleep(5000);
			
			logger.info("Passing User details....");
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString()+"@gmail.com"); // randomly generated the email
			regpage.setTelephone(randomenumber());
			
			//password should be same for both setPassword and setConfirmPassword method
			//everytime when we call method it will generate dynamic value hence we are calling only once
			String password=randomepassword();
			
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			Thread.sleep(5000);
			
			logger.info("Message Confirmation....");
			String confmsg=regpage.getConfirmationMsg();
			AssertJUnit.assertEquals(confmsg, "Your Account Has Been Created!");
			
		} 
		
		
		catch (Exception e) 
		{
			logger.error("Test failed....");
			logger.error("Debug logs....");
			AssertJUnit.fail();
		}
	}
	
	
	
	
}
