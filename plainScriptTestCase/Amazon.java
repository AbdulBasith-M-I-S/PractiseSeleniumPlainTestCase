package plainScriptTestCase;



import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Amazon {
	
	@Test
	public void amazoneTestCase() {
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

		//1. Go to https://www.zoomcar.com/chennai
		driver.get("https://www.amazon.in");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		//Search as Lg tv
		WebElement eleEnterSearch = driver.findElementByXPath("//input[@id = 'twotabsearchtextbox']");
		builder.click(eleEnterSearch).sendKeys(eleEnterSearch, "Lg tv").perform();
		
		//Click Search
		WebElement eleSearch = driver.findElementByXPath("(//input[@class ='nav-input'])[1]");
		builder.click(eleSearch).perform();
		
		//Print the Brand naame and price (With include the brand name doest not have price value) 
		List<WebElement> eleAllProductNameAndPrice = driver.findElementsByXPath("//div[@class = 'sg-row']//span[@class = 'a-size-medium a-color-base a-text-normal' or @class ='a-price-whole']");
		for (int i = 0; i < eleAllProductNameAndPrice.size(); i++) {
			String allProductNameAndPrice = eleAllProductNameAndPrice.get(i).getText();
			System.out.print(allProductNameAndPrice+"\n");
		}
		driver.close();
		
	}
}
