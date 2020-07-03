package plainScriptTestCase;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PepperFry {
	
	@Test
	public void pepperFryTestCase() throws IOException {
		
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
		
		
		//1) Go to https://www.pepperfry.com/
		driver.get("https://www.pepperfry.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//2) Mouseover on Furniture and click Office Chairs under Chairs
		
			//Close Sign In
		builder.pause(11000).perform();
		WebElement eleCloseSignIn = driver.findElementByXPath("(//a[@class ='popup-close'])[6]");
		wait.until(ExpectedConditions.visibilityOf(eleCloseSignIn));
		builder.pause(2000).click(eleCloseSignIn).perform();
		
			//Close PopUp
		WebElement eleFrame1 = driver.findElementByXPath("//div[@id ='webengage-notification-container']/iframe");
		driver.switchTo().frame(eleFrame1);
		
		WebElement eleClosePopUp = driver.findElementByXPath("//div[@id ='webklipper-publisher-widget-container-notification-close-div']/span");
		wait.until(ExpectedConditions.visibilityOf(eleClosePopUp));
		builder.pause(2000).click(eleClosePopUp).perform();
		driver.switchTo().defaultContent();
		
	        //Click Furniture
		WebElement eleFurniture = driver.findElementByXPath("(//a[text() ='Furniture'])[1]");
		builder.pause(2000).moveToElement(eleFurniture).perform();
			//Click Office Chair
		WebElement eleOfficeChair = driver.findElementByXPath("(//a[text() ='Office Chairs'])[1]");
		builder.pause(2000).click(eleOfficeChair).perform();
		
		//3) Click Executive Chairs
		WebElement eleExecutiveChair = driver.findElementByXPath("//div[@class ='cat-wrap-ttl']/h5[text() ='Executive Chairs']");
		builder.pause(2000).click(eleExecutiveChair);

		//4) Change the minimum Height as 50 in under Dimensions
		
		boolean result = false;
		int attempts = 0;
		while(attempts < 5) {
			try {
				WebElement eleMinimumHeight = driver.findElementByXPath("(//input[@class ='clipFilterDimensionHeightValue'])[1]");
				wait.until(ExpectedConditions.visibilityOf(eleMinimumHeight));
				builder.click(eleMinimumHeight).perform();
				eleMinimumHeight.clear();
				builder.sendKeys(eleMinimumHeight, "50").perform();
				result = true;
				break;
			} catch(Exception e) {
			}
			attempts++;
		}
		WebElement eleFrame2 = driver.findElementByXPath("//div[@id ='webengage-notification-container']/iframe");
		driver.switchTo().frame(eleFrame2);
		
		WebElement eleClosePopUp2 = driver.findElementByXPath("//div[@id ='webklipper-publisher-widget-container-notification-close-div']/span");
		wait.until(ExpectedConditions.visibilityOf(eleClosePopUp2));
		builder.pause(2000).click(eleClosePopUp2).perform();
		driver.switchTo().defaultContent();
	    
		//5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		WebElement elePoiseChairWishlist = driver.findElementByXPath("//a[@data-productname ='Poise Executive Chair in Black Colour']");
		wait.until(ExpectedConditions.visibilityOf(elePoiseChairWishlist));
		builder.pause(2000).click(elePoiseChairWishlist).perform();
		
		//6) Mouseover on Furniture and Click Office tables
		WebElement eleFurniture2 = driver.findElementByXPath("(//a[text() ='Furniture'])[1]");
		builder.pause(2000).moveToElement(eleFurniture2).perform();
		
		WebElement eleOfficeTable = driver.findElementByXPath("//div[@class ='hvr-col-listitem']/a[text() ='Office Tables']");
		builder.pause(2000).click(eleOfficeTable).perform();
		
		//7) Select Executive Desks
		WebElement eleExecutiveDesk = driver.findElementByXPath("//div[@class ='cat-wrap-ttl']/h5[text()='Executive Desks']");
		builder.click(eleExecutiveDesk).perform();
		
		//8) Select Price between 20000 to 40000 rs
		WebElement elePrice = driver.findElementByXPath("//input[@name ='price']/following::label[@for ='price20000-40000']");
		builder.click(elePrice).perform();
		
		//9) Add "Executive Office Table in Brown Color" to Wishlist
		
		boolean result2 = false;
		int attempts2 = 0;
		while(attempts2 < 5) {
			try {
				WebElement eleExecutiveTableWishList = driver.findElementByXPath("//a[@data-productname ='Executive Office Table in Brown Color']");
				wait.until(ExpectedConditions.visibilityOf(eleExecutiveTableWishList));
				builder.pause(2000).click(eleExecutiveTableWishList).perform();
				result2 = true;
				break;
			} catch(Exception e) {
			}
			attempts2++;
		}
		
		//10) Verify the number of items in Wishlist
		builder.pause(6000).perform();
		WebElement eleNumberOfWishList = driver.findElementByXPath("//div[@class ='wishlist_bar']/span");
		wait.until(ExpectedConditions.visibilityOf(eleNumberOfWishList));
		String NumOfWishList = eleNumberOfWishList.getText();
		System.out.println("Number of items in wishlist is "+NumOfWishList);
		if (NumOfWishList.equals("2")) {
			//wait.until(ExpectedConditions.attributeToBe(eleNumberOfWishList, "style", NumOfWishList));
			System.out.println("Hence, Number of item matches matches in wishlist");
		}
		
		//11) Navigate to Wishlist
		WebElement eleWishList = driver.findElementByXPath("//div[@class ='wishlist_bar']/a");
		builder.pause(3000).click(eleWishList).perform();
		
		//12) Get the offer Price and Coupon Code for Executive Office Table in Brown Color
		WebElement eleOfferPriceExecutiveTable = driver.findElementByXPath("(//span[@class ='txt-green'])[1]");
		String offerPriceExecutiveTable = eleOfferPriceExecutiveTable.getText();
		System.out.println("Offer Price of Table is "+offerPriceExecutiveTable);
		
		WebElement eleCouponCode = driver.findElementByXPath("(//strong[text() ='FREEDOM'])[1]");
		String couponCode = eleCouponCode.getText();
		System.out.println("Coupon Code is "+couponCode);
		
		//13) Move Executive Office Table in Brown Color only to Cart from Wishlist
		WebElement eleAddToCart = driver.findElementByXPath("(//div[@class ='action_block']/a)[1]");
		builder.pause(2000).click(eleAddToCart).perform();
		
		
		//14) Check for the availability for Pincode 600128
		WebElement eleEnterPincode = driver.findElementByXPath("//input[@class ='srvc_pin_text']");
		builder.pause(2000).sendKeys(eleEnterPincode, "600128").perform();
		
		WebElement eleCheckPincode = driver.findElementByXPath("//a[@class ='check_available']");
		builder.pause(2000).click(eleCheckPincode).perform();
		
		//15) Click on PROCEED TO PAY SECURELY from My Cart
		boolean result3 = false;
		int attempts3 = 0;
		while(attempts3 < 5) {
			try {
				WebElement eleProceedToPay = driver.findElementByXPath("//div[@id ='minicart_footer']//a");
				wait.until(ExpectedConditions.visibilityOf(eleProceedToPay));
				builder.pause(5000).click(eleProceedToPay).perform();
				result3 = true;
				break;
			} catch(Exception e) {
			}
			attempts3++;
		}
		
		
		//16) Enter the Coupon code and click Apply
		
		boolean result4 = false;
		int attempts4 = 0;
		while(attempts4 < 5) {
			try {
				WebElement eleEnterCouponCode = driver.findElementByXPath("//input[@id ='coupon_code']");
				wait.until(ExpectedConditions.visibilityOf(eleEnterCouponCode));
				builder.click(eleEnterCouponCode).pause(2000).sendKeys(eleEnterCouponCode, couponCode).perform();
				result4 = true;
				break;
			} catch(Exception e) {
			}
			attempts4++;
		}
		
		
		
		WebElement eleClickApply = driver.findElementByXPath("//input[@id ='cpn_check_btn']");
		wait.until(ExpectedConditions.visibilityOf(eleClickApply));
		builder.pause(2000).click(eleClickApply).perform();
		
		//17) Click Place Order
		WebElement elePlaceOrder = driver.findElementByXPath("(//span[@class ='ck-proceed-btn-wrap']/a)[1]");
		wait.until(ExpectedConditions.visibilityOf(elePlaceOrder));
		builder.pause(2000).click(elePlaceOrder).perform();
		
		//18) Capture the screenshot of the item under ORDER SUMMARY
		WebElement eleOrderSummary = driver.findElementByXPath("(//div[@class ='nCheckout__accrodian-header-left']/span)[1]");
		builder.pause(2000).click(eleOrderSummary).perform();
		
		long longRandomNumber = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		String stringRandomNum = Long.toString(longRandomNumber);
		System.out.println(stringRandomNum);
		
		WebElement eleOrderPicture = driver.findElementByXPath("(//input[@class ='product_details']/following::img)[1]");
		File source = eleOrderPicture.getScreenshotAs(OutputType.FILE);
		File destination = new File("./screenshot/tablePepperFry"+stringRandomNum+".png");
		FileUtils.copyFile(source, destination);
		
		//19) Close the browser
		driver.close();
		
		
		
		
	}
	
}
