package plainScriptTestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

public class Ajio {
	
	@Test
	public void ajioTestCase() {
	
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
		
		
		//1) Go to https://www.ajio.com/shop/sale
		driver.get("https://www.ajio.com/");
		//driver.get("https://www.ajio.com/shop/sale");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		//2) Enter Bags in the Search field and Select Bags in Women Handbags
		
			//Click Shop Women
		WebElement eleShopWomen = driver.findElementByXPath("(//img[@class ='btn-cat-nav img-animate'])[1]");
		builder.pause(2000).click(eleShopWomen).perform();
		
			//Close Location Access
		WebElement eleCloseLocationAccess = driver.findElementByXPath("//div[@class ='ic-close-quickview']");
		builder.pause(2000).click(eleCloseLocationAccess).perform();
			//Search Bags
		WebElement eleSearchField = driver.findElementByXPath("//input[@name ='searchVal']");
		builder.pause(2000).click(eleSearchField).pause(2000).sendKeys(eleSearchField, "Bags").perform();
			//Click Bags in Women Handbags
		WebElement eleBagsInWomen = driver.findElementByXPath("(//li[@class ='rilrtl-list-item']//span[text() ='Bags in '])[1]");
		wait.until(ExpectedConditions.visibilityOf(eleBagsInWomen));
		builder.pause(2000).click(eleBagsInWomen).perform();
		
		//3) Click on five grid and Select SORT BY as "What's New"
			//Click Five grid visible
		WebElement eleFiveGrid = driver.findElementByXPath("//div[@class ='five-grid']");
		builder.pause(2000).click(eleFiveGrid).perform();
			//Select SortBy as "Whats New"
		WebElement eleSortBy = driver.findElementByXPath("//div[@class ='filter-dropdown']/select");
		Select dropdown = new Select(eleSortBy);
		dropdown.selectByVisibleText("What's New");
		
		//4) Enter Price Range Min as 2500 and Max as 5000
			//Click Price Tab
		WebElement elePrice = driver.findElementByXPath("//span [text() ='price']");
		wait.until(ExpectedConditions.visibilityOf(elePrice));
		builder.pause(2000).click(elePrice).perform();
			//Enter Minimun Price
		WebElement eleMinimumPrice = driver.findElementByXPath("//input[@id ='minPrice']");
		builder.pause(2000).click(eleMinimumPrice).sendKeys(eleMinimumPrice, "2500").perform();
			//Enter Maximum Price
		WebElement eleMaximumPrice = driver.findElementByXPath("//input[@id ='maxPrice']");
		builder.pause(2000).click(eleMaximumPrice).sendKeys(eleMaximumPrice,"5000").perform();
			//Submit Price
		WebElement eleSubmitPrice = driver.findElementByXPath("//button[@class ='rilrtl-button ic-toparw']");
		builder.pause(2000).click(eleSubmitPrice).perform();
		
		//5) Click on the product "TOMMY HILFIGER Sling Bag with Chain Strap"
			//Select the particular product
		WebElement eleProduct = driver.findElementByXPath("//div[text() ='TOMMY HILFIGER']/following::div[text() ='Sling Bag with Chain Strap']");
		executor.executeScript("window.scrollBy(0,600)");
		builder.pause(2000).click(eleProduct).perform();
		
		
		/*6) Verify the Coupon code for the price above 2890 is applicable for your product, 
		if applicable then get the Coupon Code and the discount price for the coupon*/		
		
			//Switch to New Window
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allHandles = new ArrayList<>(allWindows);
		for (String eachWindow : allHandles) {
			driver.switchTo().window(eachWindow);
			if(driver.getTitle().equals("Buy Blue Handbags for Women by TOMMY HILFIGER Online | Ajio.com")) {
				break;
			}
		}
		
			//Product Price
		WebElement eleProductPrice = driver.findElementByXPath("//div[@class ='prod-sp']");
		String productPrice = eleProductPrice.getText();
		System.out.println(productPrice);
		String productPriceNumber = productPrice.replaceAll("[^0-9]", "");
		
			//Product Price In Integer
		int productPriceInNumber = Integer.parseInt(productPriceNumber);
		System.out.println("Product Price is "+productPriceInNumber);
		
			//Coupon is applicable
		if(productPriceInNumber>2890) {
			System.out.println("Yes, Coupon is applicable");
		}
			//Coupon Code
		WebElement eleCouponCode = driver.findElementByXPath("//div[@class ='promo-title']");
		String fullCouponCode = eleCouponCode.getText();
		String couponCode = fullCouponCode.replaceAll("Use Code", "");
		System.out.println(couponCode);
		System.out.println("Coupon Code is "+ couponCode);
			//Discount Price
		WebElement eleDiscountPrice = driver.findElementByXPath("//div[@class ='promo-discounted-price']");
		String discountPrice = eleDiscountPrice.getText().replaceAll("[^0-9]", "");
		int discountPriceInNumber = Integer.parseInt(discountPrice);
		System.out.println("Discounted Price is "+ discountPriceInNumber);
		
		
		
		//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
			//Pincode
		WebElement elePincode = driver.findElementByXPath("//span[text() ='Enter Pin-code To Know Estimated Delivery Date']");
		builder.pause(2000).click(elePincode).perform();
			//Enter Pincode
		WebElement eleEnterPincode = driver.findElementByXPath("//input[@name ='pincode']");
		builder.pause(2000).click(eleEnterPincode).sendKeys(eleEnterPincode, "560043").perform();
			//Submit Pincode
		WebElement eleSubmitPincode = driver.findElementByXPath("//button[@class ='edd-pincode-modal-submit-btn']");
		builder.pause(2000).click(eleSubmitPincode).perform();
			//Expected Delivery Date
		WebElement eleExpectedDeliveryDate = driver.findElementByXPath("//ul[@class = 'edd-message-success-details']");
		System.out.println(eleExpectedDeliveryDate.getText());
		
		
		//8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		
			//Click Other Informations
		WebElement eleOtherinformations = driver.findElementByXPath("//div[@class ='other-info-toggle']");
		builder.pause(2000).click(eleOtherinformations).perform();
		
			//Get the Customer Care with email id and address
		WebElement eleCustomerCare = driver.findElementByXPath("(//span[@class ='info-label'])[7]");
		System.out.println(eleCustomerCare.getText());
		
			//Get the Information of Customer Car with email id and address
		WebElement eleInformation = driver.findElementByXPath("(//span[@class ='other-info'])[7]");
		System.out.println(eleInformation.getText());
		
		
		//9) Click on ADD TO BAG and then GO TO BAG
		
			//Add To Bag
		WebElement eleAddToBag = driver.findElementByXPath("//div[@class ='btn-gold']/span[2]");
		builder.pause(2000).click(eleAddToBag).perform();

			//Go To Bag
		WebElement eleGoToBag = driver.findElementByXPath("//div[@class ='ic-cart ']");
		builder.pause(2000).moveToElement(eleGoToBag).perform();
			//View To Bags
		WebElement eleProceedToBag = driver.findElementByXPath("//div[@class ='mini-cart-btn']");
		builder.pause(2000).click(eleProceedToBag).perform();
		
		//10) Check the Order Total before apply coupon
			//Order Total
		WebElement eleOrderTotal = driver.findElementByXPath("//span[@class ='price-value bold-font']");
		String orderTotal = eleOrderTotal.getText().replaceAll("[^0-9]", "");
		int orderTotalInNumber = Integer.parseInt(orderTotal);
			//Verify Order Total matches Product Price
		if(orderTotalInNumber ==productPriceInNumber) {
			System.out.println("Hence, Order Price matches the Product Price");
		}
		
		//11) Enter Coupon Code and Click Apply
			//Enter the coupon code which already get value
		WebElement eleEnterCouponCode = driver.findElementByXPath("//input[@id ='couponCodeInput']");
		builder.pause(2000).click(eleEnterCouponCode).sendKeys(eleEnterCouponCode, couponCode).perform();
			//Click Apply
		WebElement eleApply = driver.findElementByXPath("//button[text() ='Apply']");
		builder.pause(2000).click(eleApply).perform();
		
		//12) Verify the discount price matches with the product price
			//Total Discount Price
		WebElement eleTotalDiscountPrice = driver.findElementByXPath("(//div[@class = 'discount']/following::div)[1]");
		String totalDiscountPrice = eleTotalDiscountPrice.getText().replaceAll("Rs. ", "");
		String totalDiscountPriceInStringNumber = totalDiscountPrice.replaceAll(",", "");
		double totalDiscountPriceInNumber = Double.parseDouble(totalDiscountPriceInStringNumber);
		System.out.println("Total Discount Price "+ totalDiscountPriceInNumber);
			//Bag Total Price
		WebElement eleBagTotal = driver.findElementByXPath("(//span[@class ='price-value'])[1]");
		String bagTotal = eleBagTotal.getText().replaceAll("Rs. ", "");
		String bagTotalInStringNumber = bagTotal.replaceAll(",", "");
		double bagTotalInNumber = Double.parseDouble(bagTotalInStringNumber);
		System.out.println("BagTotal Price "+bagTotalInNumber);
		
			//Coupon Saving
		WebElement eleCouponSaving = driver.findElementByXPath("(//span[@class ='price-value discount-price'])[1]");
		String couponSaving = eleCouponSaving.getText().replaceAll("Rs. ", "");
		System.out.println(couponSaving);
		String couponSavingInStringNumber = couponSaving.replaceAll(",", "");
		System.out.println(couponSavingInStringNumber);
		double couponSavingInNumber = Double.parseDouble(couponSavingInStringNumber);
		System.out.println("Coupon Saving Price "+couponSavingInNumber);
			//Find discount price
		double discountPriceInNumber2 = bagTotalInNumber - couponSavingInNumber;
		double discountPriceInNumber3 = Math.round(discountPriceInNumber2 * 100.0) / 100.0;
		System.out.println(discountPriceInNumber3);
			//Verify discount price is applied
		if (totalDiscountPriceInNumber == discountPriceInNumber3) {
			System.out.println("Hence, Order Price matches the Discounted Price");
		}
			//Save Price
		WebElement eleSavePrice = driver.findElementByXPath("//span[@class ='price-value discount-price']");
		String savePrice = eleSavePrice.getText();
		System.out.println("Save Price after Discount from Original Price is " +savePrice);
		
		//13) Click on Delete and Delete the item from Bag
		
			//Delete the added item
		WebElement eleDeleteItem = driver.findElementByXPath("//div[@class ='delete-btn']");
		builder.pause(2000).click(eleDeleteItem).perform();
		
			//Confirm the delete item
		WebElement eleConfirmDeleteItem = driver.findElementByXPath("(//div[@class ='delete-btn'])[2]");
		builder.pause(2000).click(eleConfirmDeleteItem).perform();
		builder.pause(5000).perform();
		
		//14) Close all the browsers
		driver.quit();
		
		
		

	}
	
	
	
}
