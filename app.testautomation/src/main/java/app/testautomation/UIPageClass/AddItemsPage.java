package app.testautomation.UIPageClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddItemsPage {
	
	WebDriver driver;
	
	public AddItemsPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public boolean searchItem(String item)
	{
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@qa='searchBar']")));

		driver.findElement(By.xpath("//*[@qa='searchBar']")).sendKeys(item);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-item-suggesion")));
		
		return true;
		
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean addItem(String brand,String item,String qty,String piecesQty )
	{
		try
		{
			
		String xpathStr = "//div[@qa='pcsAS' and text()='" + qty + "']//preceding-sibling::div//p[@qa='brandAS' and text()='" + brand + "']//following-sibling::p[contains(text(),'" + item + "')]/../..//following-sibling::div//";
		
		driver.findElement(By.xpath(xpathStr + "input[@qa='qtyAS']")).clear();
		
		driver.findElement(By.xpath(xpathStr + "input[@qa='qtyAS']")).sendKeys(piecesQty);

		driver.findElement(By.xpath(xpathStr + "button[@qa='addBtnAS']")).click();
		
		return true;
		
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean verifyItem(String itemInBasket)
	{
		try
		{
		WebElement myBasket = driver.findElement(By.xpath("//*[@qa='myBasket']"));

		// Creating object of an Actions class
		Actions action = new Actions(driver);

		// Performing the mouse hover action on the target element.
		action.moveToElement(myBasket).perform();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@qa='prodNameMB']")));

		String myBasketItem = driver.findElement(By.xpath("//*[@qa='prodNameMB']")).getText().trim();
		
		if(myBasketItem.contains(itemInBasket))
		{
			System.out.println("The Ordered item is displayed correctly in the basket");
			return true;
		}
		else
		{
			System.out.println("Failed:" + myBasketItem);
			return false;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

}
