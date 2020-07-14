package plainScriptTestCase;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AutoPortal {
	
	@Test
	public void autoPortalTestcase() {
		
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
			
			//1. Go to https://autoportal.com/
			driver.get("https://autoportal.com/");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			//2) Select City as Chennai
				//Click on Change City
			WebElement eleChangeCity = driver.findElementByXPath("//span[@class ='change']");
			builder.pause(2000).click(eleChangeCity).perform();
			
				//Enter the City as Chennai
			WebElement eleEnterCity = driver.findElementByXPath("//input[@id ='ac_user_city']");
			eleEnterCity.clear();
			builder.pause(2000).click(eleEnterCity).sendKeys(eleEnterCity, "Chennai").perform();
			
				//Click on the dropdown list as Chennai
			WebElement eleCity = driver.findElementByXPath("//li[@class = 'ui-menu-item']");
			builder.pause(2000).click(eleCity).perform();
			
				//Click on Cofirm City
			WebElement eleConfirmCity = driver.findElementByXPath("//span[text() ='Confirm City']");
			builder.pause(2000).click(eleConfirmCity).perform();
			
			//3) Select Car as Renault
			boolean result = false;
			int attempts = 0;
			while(attempts < 5) {
				try {
					WebElement eleSelectBrand = driver.findElementByXPath("(//select[@class ='field m_b-15'])[1]");
					wait.until(ExpectedConditions.visibilityOf(eleSelectBrand));
					Select dropDown1 = new Select(eleSelectBrand);
					dropDown1.selectByVisibleText("Renault");
					
					result = true;
					break;
				} catch(Exception e) {
				}
				attempts++;
			}
			
			//4) Select Model as Arkana
			boolean result2 = false;
			int attempts2 = 0;
			while(attempts2 < 5) {
				try {
					WebElement eleSelectModel = driver.findElementByXPath("(//select[@class ='field m_b-15'])[2]");
					wait.until(ExpectedConditions.visibilityOf(eleSelectModel));
					Select dropDown2 = new Select(eleSelectModel);
					dropDown2.selectByVisibleText("Arkana");
					
					result2 = true;
					break;
				} catch(Exception e) {
				}
				attempts2++;
			}
			
			//5) Click on Find New Car
			WebElement eleFindNewCar = driver.findElementByXPath("(//input[@class ='red_but'] )[1]");
			builder.pause(2000).click(eleFindNewCar).perform();
			
			//6) Get the Price of the car
			WebElement elePrice = driver.findElementByXPath("//div[@class ='nm_price m_b-10']");
			System.out.println(elePrice.getText());
			
			//7) Close the browser
			driver.close();
			
			
	}
	
	

}
