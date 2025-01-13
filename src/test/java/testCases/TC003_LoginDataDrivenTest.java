package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.Baseclass;
import utilities.DataProviders;

/*
 * Data is valid  --> Login Success --> Test Pass --> Logout
Data is valid -->  Login Fail  --> Test Fail

Data is invalid --> Login Success --> Test Fail --> Logout
Data is invalid --> Login Fail --> Test Pass
 */

public class TC003_LoginDataDrivenTest extends Baseclass
{
	//HomePage
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")//here dataProvider is present in different class and package so to include we added an extra attribute dataProviderClass whose value should be class name where data provider is present and with extension as .class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("*****************Starting TC_002_LoinTest ****************");
		try 
		{
			//HomePage
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.ClickLogin();
			
			Thread.sleep(5000);
			
			//LoginPage
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();
			Thread.sleep(5000);
			
			//MyAccount
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage=macc.isMyAccountPageExists();
			Thread.sleep(5000);
			
			//Data is valid  --> Login Success --> Test Pass --> Logout
			//Data is valid -->  Login Fail  --> Test Fail
			if (exp.equalsIgnoreCase("valid")) 
			{
				if (targetPage==true) 
				{
					macc.clicklogout();
					Assert.assertTrue(true);
				}
				else 
				{
					Assert.assertTrue(false);
				}
			} 
			
			//Data is invalid --> Login Success --> Test Fail --> Logout
			//Data is invalid --> Login Fail --> Test Pass
			if (exp.equalsIgnoreCase("Invalid")) 
			{
				if (targetPage==true) 
				{
					macc.clicklogout();
					Assert.assertTrue(false);
				}
				else 
				{
					Assert.assertTrue(true);
				}
			} 
			
		} 
			
			
		catch (Exception e) 
		{
			Assert.fail();
		}
			
			logger.info("*****************Completed TC_002_LoinTest ****************");
			
	}
	

}
