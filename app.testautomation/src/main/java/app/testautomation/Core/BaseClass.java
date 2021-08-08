package app.testautomation.Core;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	
	public WebDriver driver;
	
	public WebDriver getDriver()
	{
		try
		{
		
		System.setProperty("webdriver.chrome.driver", "./browserdrivers/chromedriver.exe");
	    driver = new ChromeDriver();		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return driver;
		
	}
	
	public void closeDriver(WebDriver driver)
	{
		driver.quit();
	}
	
	public boolean launchAppURL(WebDriver driver,String url)
	{
		try
		{
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

}
