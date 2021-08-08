package app.testautomation.UI;

import org.testng.annotations.Test;

import app.testautomation.Core.BaseClass;
import app.testautomation.UIPageClass.AddItemsPage;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Add_Verify_MyBasketItems extends BaseClass {
	
  WebDriver driver;
  
  String appURL = "https://www.bigbasket.com/";
  String searchProduct = "Rice";
  String brand = "BB Royal";
  String item = "Raw, Sona Masoori";
  String qty = "5 kg";
  String pieces = "3";
  String itemInBasket = brand + " " + searchProduct + " - " + item + " " + qty;
  
  
  @BeforeTest
  public void beforeTest() {
	  driver= getDriver();	  
  }
	
  @Test
  public void addVerifyItemsInBasket() {	  
	 
	  AddItemsPage addproduct = new  AddItemsPage(driver);
	  
	  boolean blnFlag;
	 
	  //Step1 : Launching the application
	  blnFlag = launchAppURL(driver, appURL);
	  if(!blnFlag)
	  {
		  Assert.fail("Application Launch Failed");
	  }
	  else
		  Reporter.log("Application " + appURL + " Launched");
	  
	  //Step 2 : Search an Item
	  blnFlag = addproduct.searchItem(searchProduct);
	  if(!blnFlag)
	  {
		  Assert.fail("Item Search Failed");
	  }
	  else
		  Reporter.log(searchProduct + " - Item Searched successfully ");
	  
	  //Step 3 : Add an Item to the Basket
	  blnFlag = addproduct.addItem(brand, item, qty, pieces);
	  if(!blnFlag)
	  {
		  Assert.fail("Unable to add a product to the basket:Failed");
	  }
	  else
		  Reporter.log(itemInBasket + " - Item added successfully ");
	  
	  //Step 4 : verify the item is added correctly in the Basket
	  blnFlag = addproduct.verifyItem(itemInBasket);
	  if(!blnFlag)
	  {
		  Assert.fail("The added product is not displayed correctly in the basket");
	  }
	  else
		  Reporter.log(itemInBasket + " - The added item is displayed in Basket correctly");
	  
  }
  

  @AfterTest
  public void afterTest() {
	  
	  closeDriver(driver);
  }

}
