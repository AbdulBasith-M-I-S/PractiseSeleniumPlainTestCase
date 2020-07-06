package plainScriptTestCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Zalando {
	
	@Test
	public void zalandoTestCase() {
		
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
		
		
		//1) Go to https://www.zalando.com/
		driver.get("https://www.zalando.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//2) Get the Alert text and print it
		//builder.pause(2000).perform();
		Alert alert1 = driver.switchTo().alert();
		String alertText1 = alert1.getText();
		System.out.println("Alert is displayed as "+alertText1);
		alert1.accept();
		
		//3) Close the Alert box and click on Zalando.uk
		WebElement eleZalandoUK = driver.findElementByXPath("//a[@class = 'nav_link nav_link-gb']");
		builder.pause(2000).click(eleZalandoUK).perform();
		
		
		//4) Click Women--> Clothing and click Coats
			//Allow Cookies Sweet Alert
		boolean result = false;
		int attempts = 0;
		while(attempts < 5) {
			try {
				WebElement eleSweetAlert1 = driver.findElementByXPath("//button[@class = 'uc-btn uc-btn-primary']");
				wait.until(ExpectedConditions.visibilityOf(eleSweetAlert1));
				builder.pause(5000).click(eleSweetAlert1).perform();
				result = true;
				break;
			} catch(Exception e) {
			}
			attempts++;
		}
		
		/*WebElement eleWomenTab = driver.findElementByXPath("(//span[text() ='Women'])[1]");
		builder.pause(2000).click(eleWomenTab).perform();*/
			//Click on Clothing Tab
		WebElement eleClothing = driver.findElementByXPath("//span[text() ='Clothing']");
		builder.pause(2000).moveToElement(eleClothing).perform();
			//Click on Coats
		WebElement eleCoats = driver.findElementByXPath("//span[text() ='Coats']");
		builder.pause(2000).click(eleCoats).perform();
		
		//5) Choose Material as cotton (100%) and Length as thigh-length
			//Click Material Filter
		WebElement eleMaterial = driver.findElementByXPath("//span[text() ='Material']");
		builder.pause(2000).click(eleMaterial).perform();
			//Choose Cotton 100%
		WebElement elePureCotton = driver.findElementByXPath("//span[text() ='cotton (100%)']");
		builder.pause(2000).click(elePureCotton).perform();
			//Save
		WebElement eleSave1 = driver.findElementByXPath("//button[text() ='Save']");
		builder.pause(2000).click(eleSave1).perform();
			
		
			//Click Length Filter
		boolean result2 = false;
		int attempts2 = 0;
		while(attempts2 < 5) {
			try {
				
				WebElement eleLength = driver.findElementByXPath("//span[text() ='Length']");
				wait.until(ExpectedConditions.visibilityOf(eleLength));
				builder.pause(2000).click(eleLength).perform();
				
				result2 = true;
				break;
			} catch(Exception e) {
			}
			attempts2++;
		}
		
			//Choose ThighLength
		WebElement eleThighLength = driver.findElementByXPath("//span[text() ='thigh-length']");
		builder.pause(2000).click(eleThighLength).perform();
			//Save
		WebElement eleSave2 = driver.findElementByXPath("//button[text() ='Save']");
		builder.pause(2000).click(eleSave2).perform();
		
			//Click Color Filter
		boolean result3 = false;
		int attempts3 = 0;
		while(attempts3 < 5) {
			try {
				
				WebElement eleChooseColor = driver.findElementByXPath("//span[text() ='Colour']");
				wait.until(ExpectedConditions.visibilityOf(eleChooseColor));
				builder.pause(2000).click(eleChooseColor).perform();
				
				result3 = true;
				break;
			} catch(Exception e) {
			}
			attempts3++;
		}
			//Choose Black Color
		WebElement eleBlack = driver.findElementByXPath("//span[text() ='Black']");
		wait.until(ExpectedConditions.visibilityOf(eleBlack));
		builder.pause(2000).click(eleBlack).perform();
			//Click Save
		WebElement eleSave3 = driver.findElementByXPath("//button[text() ='Save']");
		builder.pause(2000).click(eleSave3).perform();
		
		//6) Click on JUNAROSE - by VERO MODA
			//Click on JUNAROSE product
		boolean result4 = false;
		int attempts4 = 0;
		while(attempts4 < 5) {
			try {
				WebElement eleJunarose = driver.findElementByXPath("//div[text() ='JUNAROSE - by VERO MODA']");
				wait.until(ExpectedConditions.visibilityOf(eleJunarose));
				builder.pause(2000).click(eleJunarose).perform();
						
				result4 = true;
				break;
			} catch(Exception e) {
			}
			attempts4++;
		}	
		
		//7) Click Color as Black and Size as 'M' Under Manufacture Sizes
			//Click Size Filter
		WebElement eleChooseSize = driver.findElementByXPath("//button[@id ='picker-trigger']/span");
		builder.pause(2000).click(eleChooseSize).perform();
			//Choose Manufacturing Size
		WebElement eleManufacturingSize = driver.findElementByXPath("(//input[@id ='manufacturer']/following::span)[1]");
		builder.pause(2000).click(eleManufacturingSize).perform();
			//Click M Size
		WebElement eleMSize = driver.findElementByXPath("//span[text() ='M']");
		builder.pause(2000).click(eleMSize).perform();
		
		
		//8) Add to bag only if Standard Delivery is free
			//Get the status of delivery
		WebElement eleDeliveryFree = driver.findElementByXPath("(//button[@aria-label ='Free']/span[text() ='Free'])[1]");
		wait.until(ExpectedConditions.visibilityOf(eleDeliveryFree));
		String DeliveryFree = eleDeliveryFree.getText();
			//If delivery is free click on ADD TO BAG
		if (DeliveryFree.equals("Free")) {
			System.out.println("Standard Delivery is Free");
			WebElement eleAddToBag = driver.findElementByXPath("//button[@aria-label ='Add to bag']/span");
			builder.pause(2000).click(eleAddToBag).perform();
		}
			
		//9) Mouse over on Your Bag and Click on "Go to Bag"
			//Click on YOUR BAG
		WebElement eleYourBag = driver.findElementByXPath("//span[@class ='z-navicat-header_navToolLabel']//span[text() ='Your bag']");
		builder.pause(2000).moveToElement(eleYourBag).perform();
			//Click On GO TO BAG
		WebElement eleGoToBag = driver.findElementByXPath("//div[@class ='z-1-button__content']");
		builder.pause(2000).click(eleGoToBag).perform();
		
		//10) Read the Estimated Deliver Date and print
			//Get the estimated delivery date
		WebElement eleDeliverDate = driver.findElementByXPath("//div[@data-id ='delivery-estimation']/span");
		String DeliverDate = eleDeliverDate.getText();
		System.out.println("Estimated Delivery Date is "+DeliverDate);
		
		//11) Click on 'Go To Checkout'
			//Click on Go To CheckOut
		WebElement eleGoToCheckOut = driver.findElementByXPath("(//div[@class ='z-1-button__content'])[3]");
		builder.pause(2000).click(eleGoToCheckOut).perform();
		
		//12) Enter your email
			//Enter Email address
		boolean result5 = false;
		int attempts5 = 0;
		while(attempts5 < 5) {
			try {
				WebElement eleEmail = driver.findElementByXPath("//input[@id ='login.email']");
				wait.until(ExpectedConditions.visibilityOf(eleEmail));
				builder.pause(2000).click(eleEmail).sendKeys(eleEmail, "testemail@test.com").perform();
						
				result5 = true;
				break;
			} catch(Exception e) {
			}
			attempts5++;
		}

		//13) Click on Login button
			//Click Login button
		WebElement eleLogin = driver.findElementByXPath("//button[@data-testid ='login_button']/span");
		wait.until(ExpectedConditions.visibilityOf(eleLogin));
		builder.pause(2000).click(eleLogin).perform();
		
		
		//14) Print the error message
			//Error Message is displayed because password is not entered get the error message
		WebElement eleErrorMsg = driver.findElementByXPath("//div[@data-validation='error']/span[text() ='This field is required']");
		String errorMsg = eleErrorMsg.getText();
		System.out.println("The error message is "+errorMsg);
		
		//15) Close the browser
		driver.close();
		
		

	}
	
	

}
