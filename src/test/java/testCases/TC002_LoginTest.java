package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.Baseclass;

public class TC002_LoginTest extends Baseclass
{
	@Test(groups = {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("*****************Starting TC_002_LoinTest ****************");
		
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		Thread.sleep(5000);
		hp.clickMyAccount();
		hp.ClickLogin();
		
		Thread.sleep(5000);
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(properties.getProperty("email"));
		lp.setPassword(properties.getProperty("password"));
		lp.clickLogin();
		
		Thread.sleep(5000);
		
		MyAccountPage macc=new MyAccountPage(driver);
		
		boolean targetPage=macc.isMyAccountPageExists();
		Assert.assertEquals(targetPage, true, "Login Failed");
		
		}
		
		catch (Exception e) 
		{
			Assert.fail();
		}
		
		logger.info("*****************Completed TC_002_LoinTest ****************");
	}
}
