package testBase;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
//This base class consists of methods which are useful to all other classes
//Utility class
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Baseclass 
{
	
public static WebDriver driver;
public Logger logger;  //Log4j
public Properties properties;
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws InterruptedException, IOException
	{
		FileReader freader=new FileReader("/Users/spandanakn/eclipse-workspace/OpenCartV121/src/test/resources/config.properties");
		properties=new Properties();
		properties.load(freader);
		
		logger=LogManager.getLogger(this.getClass());
		
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			//os
			if (os.equalsIgnoreCase("MAC")) 
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("Windows")) 
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else
			{
				System.out.println("No Operating System found");
			}
			//browser
			switch (br.toLowerCase()) 
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "safari":capabilities.setBrowserName("safari");break;
			case "edge":capabilities.setBrowserName("edge");break;
			default: System.out.println("Invalid browser name..."); return;
			}
			driver=new RemoteWebDriver(new URL("http://192.168.225.115:4444"),capabilities);
		}
		
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch (br.toLowerCase()) 
		{
		case "chrome" : driver=new ChromeDriver();break;
		case "safari" : driver=new SafariDriver();break;
		case "edge" : driver=new EdgeDriver();break;
		default: System.out.println("Invalid browser name...");return;// return means execution will be stopped completely
		}
		
		}
		
		//driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("appURL1"));//reading url from config.properties file
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	
	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void teardown()
	{
		driver.quit();
	}
	
	//To generate random email we are writing seperate method
		//User defined method
		public String randomeString()
		{
			String generatedString=RandomStringUtils.randomAlphabetic(5);//RandomStringUtils is the inbuilt class of Java coming from commons.lang3 dependencies
			return generatedString;
		}
		
		//To generate dynamic Number
		public String randomenumber() 
		{
			String generatednumber=RandomStringUtils.randomNumeric(10);
			return generatednumber;	
		}
		
		//To generate dynamic password
		public String randomepassword() 
		{
			String generatedString=RandomStringUtils.randomAlphabetic(3);
			String generatednumber=RandomStringUtils.randomNumeric(3);
			return (generatedString+generatednumber);	
		}

		public String captureScreen(String tname) throws IOException 
		{
		    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		    String targetFilePath = "/Users/spandanakn/eclipse-workspace/OpenCartV121/screenshots"+ tname + "-" + timeStamp + ".png";
		    File targetFile = new File(targetFilePath);
		    sourceFile.renameTo(targetFile);
		    return targetFilePath;
		}
}
