package plainScriptTestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MakeMyTrip {
	
	@Test
	public void makeMyTripTest() {

		
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
		
		//1) Go to https://www.makemytrip.com/
		
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		

		//2) Click Hotels
		
		WebElement eleLogin = driver.findElementByXPath("//div[@class = 'makeFlex column flexOne whiteText latoBold']");
		builder.pause(2000).click(eleLogin).perform();
		
		WebElement eleHotels = driver.findElementByXPath("(//span[@class ='chNavText darkGreyText'])[2]");
		wait.until(ExpectedConditions.visibilityOf(eleHotels));
		builder.pause(2000).click(eleHotels).perform();
		
		//3) Enter city as Goa, and choose Goa, India
		
		WebElement eleCity = driver.findElementByXPath("//span[@class ='lbl_input latoBold  appendBottom5']");
		builder.pause(2000).click(eleCity).perform();
		
		boolean result = false;
		int attempts = 0;
	    while(attempts < 5) {
	        try {
	        	WebElement eleEnterCity = driver.findElementByXPath("//div[@role ='combobox']/input");
				wait.until(ExpectedConditions.visibilityOf(eleEnterCity));
				builder.pause(2000).click(eleEnterCity).pause(2000).perform();
	    	    builder.sendKeys(eleEnterCity, "Goa").pause(2000).sendKeys(Keys.TAB).perform();
	            result = true;
	            break;
	        } catch(Exception e) {
	        }
	        attempts++;
	    }
		
		//4) Enter Check in date as Next month 15th (July 15) and Check out as start date+4
		
			//CheckIn date
		String checkInDate = "Jul 15 2020";
		WebElement eleCheckInDate = driver.findElementByXPath("//div[@aria-label ='Wed "+checkInDate+"']");
		builder.pause(2000).click(eleCheckInDate).perform();
			//CheckOut Date
		String checkOutDate = "Jul 18 2020";
		WebElement eleCheckOutDate = driver.findElementByXPath("//div[@aria-label ='Sat "+checkOutDate+"']");
		builder.pause(2000).click(eleCheckOutDate).perform();
		
		
		//5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button
		
		WebElement eleRoomGuest = driver.findElementByXPath("//input[@id = 'guest']");
		builder.pause(2000).click(eleRoomGuest).perform();
			//Adult
		String adult = "adults-2";
		WebElement eleAdult = driver.findElementByXPath("//ul[@class = 'guestCounter font12 darkText']/li[@data-cy = '"+adult+"']");
		builder.pause(2000).click(eleAdult).perform();
			//Children
		String children = "children-1";
		WebElement eleChildren = driver.findElementByXPath("//ul[@class ='guestCounter font12 darkText']/li[@data-cy ='"+children+"']");
		builder.pause(2000).click(eleChildren).perform();
			//Children Age
		WebElement eleSelectAge = driver.findElementByXPath("//select [@class = 'ageSelectBox']");
		Select dd = new Select(eleSelectAge);
		dd.selectByVisibleText("12");
			//Apply
		WebElement eleApply = driver.findElementByXPath("//button [@class ='primaryBtn btnApply']");
		builder.pause(2000).click(eleApply).perform();
		
		//6) Click Search button
		WebElement eleSearch = driver.findElementById("hsw_search_button");
		builder.pause(2000).click(eleSearch).perform();
		
		//7) Select locality as Baga
		
		builder.click().perform();
		
		WebElement eleLocality = driver.findElementByXPath("//input[@id ='mmLocality_checkbox_35']/following::label[1]");
		builder.pause(2000).click(eleLocality).perform();

		
		
		//8) Select user rating as 3&above(good) under Select Filters
		WebElement eleUserRating = driver.findElementByXPath("//span[@class ='checkmarkOuter']/following::label[text() ='3 & above (Good)']");
		builder.pause(2000).click(eleUserRating).perform();
		
		//9) Select Sort By: Price-Low to High
		WebElement eleSortBy = driver.findElementByXPath("//div[@id ='hlistpg_sortby_option']/span");
		builder.pause(2000).click(eleSortBy).perform();
		
		WebElement eleLowToHigh = driver.findElementByXPath("//div[@id ='hlistpg_sortby_option']/ul/li[text() ='Price - Low to High']");
		builder.pause(2000).click(eleLowToHigh).perform();
		
		
		//10) Click on the first resulting hotel and go to the new window
		WebElement eleFirstResultHotel = driver.findElementByXPath("(//p[@id ='hlistpg_hotel_name']/span)[2]");
		builder.pause(2000).click(eleFirstResultHotel).perform();
		
		driver.manage().deleteAllCookies();
		
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allHandles = new ArrayList<String>(allWindows);
		for (String eachWindow : allHandles) {
			driver.switchTo().window(eachWindow);
			if(driver.getTitle().equals("Woodside Retreat (Managed by HNH Homes) | Hotel Details Page | MakeMyTrip.com")) {
				break;
			}
		}
		
		
		//11) Print the Hotel Name
		
		WebElement eleHotelName = driver.findElementByXPath("//span[@class ='txtHeading font22 latoBlack blackText']/h1");
		System.out.println(eleHotelName.getText());
		
		
		//12) Click VIEW THIS COMBO button under Recommended Combo
		
		WebElement eleViewCombo = driver.findElementById("detpg_multi_view_combo");
		builder.pause(2000).click(eleViewCombo).perform();
		
		//13) Click on BOOK THIS COMBO button
		
		WebElement eleBookThisCombo = driver.findElementByXPath("//span[@id = 'detpg_book_combo_btn']");
		builder.pause(2000).click(eleBookThisCombo).perform();
		
		//14) Print the Total Payable amount
		
		WebElement eleTotalAmount = driver.findElementById("revpg_total_payable_amt");
		System.out.println("The total payable amount is "+eleTotalAmount.getText());
		
		WebElement eleDialogBox = driver.findElementByXPath("//span[@class ='close']");
		wait.until(ExpectedConditions.visibilityOf(eleDialogBox));
		try {
			
			builder.pause(8000).click(eleDialogBox).perform();
		} catch (Exception e) {
			System.out.println("The exception is "+e.getMessage());
		}
		
		//15) Close the browser
		driver.quit();
		
		
		
		
}
}
