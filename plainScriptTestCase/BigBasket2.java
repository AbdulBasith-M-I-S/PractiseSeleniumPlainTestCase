package plainScriptTestCase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BigBasket2 {
	
	
	@Test
	public void bigBasket2TestCase() {
		//Set driver path
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

			//To use chrome options
			ChromeOptions options = new ChromeOptions();
			//To disable notification
			options.addArguments("--disable-notifications");
			//To run in incognito mode
			options.addArguments("--incognito");


		//To open Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);

		//WebDriverWait
		WebDriverWait wait= new WebDriverWait(driver,20);

		//Action Class
		Actions builder = new Actions(driver);

		//JavaScripExector
		JavascriptExecutor executor = (JavascriptExecutor)driver;

		//********************************************************************************
		
		//1) Go to https://www.bigbasket.com/
		driver.get("https://www.bigbasket.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//2) Mouse over on  Shop by Category
		
			//Click on Location
		WebElement eleLocation = driver.findElementByXPath("//span[@class = 'hvc' ]/span[1]");
	    builder.pause(2000).click(eleLocation).pause(2000).perform();
	    	//Click on City
	    WebElement eleCity = driver.findElementByXPath("(//span[@class = 'btn btn-default form-control ui-select-toggle'])[1]");
	    builder.pause(2000).click(eleCity).pause(2000).perform();
	    	//Enter City as Chennai
	    WebElement eleEnterCity = driver.findElementByXPath("(//input [@type = 'search'] )[1]");
	    builder.pause(2000).sendKeys(eleEnterCity,"Chennai").sendKeys(Keys.TAB).pause(2000).perform();
	    	//Click on Continue
	    WebElement eleContinue = driver.findElementByXPath("(//button [@name = 'skipandexplore' ] ) [1]");
	    builder.pause(2000).click(eleContinue).pause(2000).perform();

			//Go to Category
	    WebElement eleCategory = driver.findElementByXPath("//li[@class = 'dropdown full-wid hvr-drop']/a");
	    builder.pause(3000).moveToElement(eleCategory).pause(3000).perform();
	    
		//3) Go to Beverages and Fruit juices & Drinks
	    	//Go to Beverages
	    WebElement eleBeverages = driver.findElementByXPath("(//li[@data-submenu-id ='beverages']/a)[2]");
	    builder.pause(2000).moveToElement(eleBeverages).pause(2000).perform();
	    	//Go to Fresh Juice
	    WebElement eleFreshJuice = driver.findElementByXPath("(//a[text() ='Fruit Juices & Drinks'])[2]");
	    builder.pause(2000).moveToElement(eleFreshJuice).pause(2000).perform();
	    
		//4) Click on JUICES
	    	//Click on Juices
	    WebElement eleJuice = driver.findElementByXPath("(//a[text() ='Juices'])[2]");
	    builder.pause(2000).click(eleJuice).pause(2000).perform();
	    
		//5) Click Tropicana and Real under Brand
	    	//Check brand as Real
	    WebElement eleReal = driver.findElementByXPath("(//span[@ng-bind = 'brand.display_name' and text() = 'Real']/preceding-sibling::span/i)[1]");
	    builder.pause(2000).click(eleReal).pause(2000).perform();
	    	//Check brand as Tropicana
	    WebElement eleTropicana = driver.findElementByXPath("(//span[@ng-bind = 'brand.display_name' and text() = 'Tropicana']/preceding-sibling::span/i)[1]");
	    builder.pause(2000).click(eleTropicana).pause(2000).perform();
	    
		//6) Check count of the products from each Brands and total count
	    	//Count No of Tropicana is available
	    List<WebElement> eleAllTropicana = driver.findElementsByXPath("//h6[@ng-bind ='vm.selectedProduct.p_brand' and text() ='Tropicana']");
	    int count =0;
	    for (int i = 0; i < eleAllTropicana.size(); i++) {
		String Tropicana = eleAllTropicana.get(i).getText();
		if (Tropicana.equals("Tropicana")) {
			count++;
		}
	    }
	    System.out.println("Tropicana brand count is "+count);
	    
	    	//Count No of Real is available
	    List<WebElement> eleAllReal = driver.findElementsByXPath("//h6[@ng-bind ='vm.selectedProduct.p_brand' and text() ='Real']");
	    int count2 =0;
	    for (int i = 0; i < eleAllReal.size(); i++) {
		String Real = eleAllReal.get(i).getText();
		if (Real.equals("Real")) {
			count2++;
		}
	    }
	    System.out.println("Real brand count is "+count2);
		
	    //7) Check whether the products is available with Add button
	    	//Check No of product without add button or All the product has Add button
	    List<WebElement> eleAllNotifyButton = driver.findElementsByXPath("//button [@qa ='NM']");
	    List<WebElement> eleAllAddButton = driver.findElementsByXPath("//button [@qa ='add']");
	    System.out.println("Number of Product with Add button = "+eleAllAddButton.size());
	    try {
			System.out.println("Number of Product without Add button = "+ eleAllNotifyButton.size());
		} catch (Exception e) {
			System.out.println("All the product has Add Button");
			e.printStackTrace();
		}
	    
		//8) Add the First listed available product
	    	//Click on Add Button
	    WebElement eleAddButton = driver.findElementByXPath("(//button [@qa ='add'] )[1]");
	    builder.pause(2000).click(eleAddButton).pause(2000).perform();
	    	//Wait until the added succesfully notification disapper
	    builder.pause(5000).perform();
	    
		//9) Click on Change Address
	    	//Click on Change Address
	    executor.executeScript("window.scrollBy(0,-500)");
	    WebElement eleAddress = driver.findElementByXPath("//span [ @ng-bind = 'vm.user.currentAddress.city_name']");
	    builder.pause(3000).click(eleAddress).pause(3000).perform();
	   
	    
		//10) Select Chennai as City, Alandur-600016,Chennai as Area  and click Continue
	    	//Select Area as Alandur
	    WebElement eleArea = driver.findElementByXPath("(//input [@qa = 'areaInput'] )[1]");
	    builder.pause(3000).click(eleArea).sendKeys(eleArea,"Alandur").pause(3000).sendKeys(eleArea,Keys.TAB).pause(3000).perform();
	    
	    /*WebElement eleClickArea = driver.findElementByXPath("//li[@class = 'ng-scope active']");
	    builder.pause(3000).click(eleClickArea).pause(3000).perform();*/
	    	//Click on Contiune
	    WebElement eleContinue2 = driver.findElementByXPath("(//button[text() = 'Continue' ] )[1]");
	    builder.pause(3000).moveToElement(eleContinue2).click(eleContinue2).perform();
	    
		//11) Mouse over on My Basket print the product name, count and price
	    	//View Basket Icon
	    WebElement eleViewBasketIcon = driver.findElementByXPath("//span[@class ='basket-content']");
	    builder.pause(2000).moveToElement(eleViewBasketIcon).pause(2000).perform();
	    	//Get the product Name
	    WebElement eleProductName = driver.findElementByXPath("//div[@class ='product-name']");
	    System.out.println("Product Name is added = "+eleProductName.getText());
	    	//Get the count of Product
	    WebElement eleCountOfProduct = driver.findElementByXPath("//div[@class ='product-qty ng-binding']");
	    String countOfProductWithPrice = eleCountOfProduct.getText();
	    String[] splitCountOfProductWithPrice = countOfProductWithPrice.split(" ");
	    System.out.println("No of product is added = "+ splitCountOfProductWithPrice[0]);
	    //System.out.println("No of product is added"+eleCountOfProduct.getText());
	    	//Get the Price of Product
	    WebElement elePriceOfProduct = driver.findElementByXPath("//span[@qa ='priceMB']");
	    System.out.println("Price of the product = "+elePriceOfProduct.getText());
	    
	    
		//12) Click View Basket and Checkout
	    WebElement eleViewBasket = driver.findElementByXPath("//button[@qa='viewBasketMB']");
	    builder.pause(3000).click(eleViewBasket).perform();
	    
		//13) Click the close button and close the browser
	    WebElement eleClose = driver.findElementByXPath("//span[@class ='login-icon login-icon-close']");
	    builder.pause(3000).click(eleClose).perform();
	    
	    //14) Close the chrome browser
	    driver.quit();
		
		
		

	}
	
}
