package plainScriptTestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Trivago {
	
	@Test
	public void trivagoTestCase() throws InterruptedException {
		
		
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



		//1) Go to https://www.trivago.com/
		driver.get("https://www.trivago.in/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//2) Type Agra in Destination and select Agra, Uttar Pradesh
		WebElement eleSelectPlace = driver.findElementByXPath("//input[@id = 'querytext' ]");
		builder.sendKeys(eleSelectPlace, "Agra").pause(3000).sendKeys(Keys.TAB).perform();
		
		//3) Choose July 15 as check in and July 30 as check out

			//ExpectedDate for check in
		String expectedDateForCheckIn = "2020-07-15";
		List<WebElement> eleAllRows = driver.findElementsByXPath("//table[@class = 'cal-month' ]//tbody/tr/td/time");
		//AllCalendarDate
		List<String> checkDate = new ArrayList<>();
		for (int i = 0; i < eleAllRows.size(); i++) {
			WebElement eleEachRow = eleAllRows.get(i);
			checkDate.add(eleEachRow.getAttribute("datetime"));
		}
		System.out.println(checkDate);
		
		//Click the check in date
		for (int i = 0; i < checkDate.size(); i++) {
			if(checkDate.get(i).equals(expectedDateForCheckIn)) {
				WebElement eleCheckInDate = driver.findElementByXPath("//time [@datetime ='"+expectedDateForCheckIn+"']");
				System.out.println(eleCheckInDate.getAttribute("datetime"));
				wait.until(ExpectedConditions.visibilityOf(eleCheckInDate));
				executor.executeScript("arguments[0].click();", eleCheckInDate);
			}
		}

			//ExpectedDate for check out
		Thread.sleep(3000);
		String expectedDateForCheckOut = "2020-07-30";
		//Click the check out date
		for (int i = 0; i < checkDate.size(); i++) {
			if(checkDate.get(i).equals(expectedDateForCheckOut)) {
				WebElement eleCheckOutDate = driver.findElementByXPath("//time [@datetime ='"+expectedDateForCheckOut+"']");
				System.out.println(eleCheckOutDate.getAttribute("datetime"));
				wait.until(ExpectedConditions.visibilityOf(eleCheckOutDate));
				executor.executeScript("arguments[0].click();", eleCheckOutDate);

			}
		}
		
		
		/*//4) Select Room as Family Room
		WebElement eleFamilyRoom = driver.findElementByXPath("//span[text() = 'Family rooms']");
		wait.until(ExpectedConditions.visibilityOf(eleFamilyRoom));
		builder.pause(3000).moveToElement(eleFamilyRoom).click(eleFamilyRoom).perform();*/

		//5) Choose Number of Adults 2, Childern 1 and set Child's Age as 4
			//No. of Children
		WebElement eleChooseNoOfChildren = driver.findElementByXPath("//input[@id ='children-input']");
		builder.pause(2000).click(eleChooseNoOfChildren).sendKeys(eleChooseNoOfChildren,"1").sendKeys(eleChooseNoOfChildren, Keys.ENTER).perform();
			//Age of Children
		WebElement eleAgeInfo = driver.findElementByXPath("//p[@class = 'm-0 font-bright font-tiny']");
		builder.pause(2000).click(eleAgeInfo).perform();
		WebElement eleAgeOfChild = driver.findElementByXPath("//select[@name= 'mf-select-children-age-0']");
		Select dd2 = new Select(eleAgeOfChild);
		dd2.selectByVisibleText("4");
		
			//Click Apply
		WebElement eleApply = driver.findElementByXPath("(//button[text() ='Apply'])[1]");
		builder.pause(2000).click(eleApply).perform();
		
		//6) Click Confirm button and click Search
			//Click Search
		WebElement eleConfirm = driver.findElementByXPath("//button[@class ='btn btn--primary btn--regular search-button js-search-button']");
		wait.until(ExpectedConditions.visibilityOf(eleConfirm));
		builder.pause(3000).moveToElement(eleConfirm).click(eleConfirm).perform();

		//7) Select Accommodation type as Hotels only and choose 4 stars
			//Click Accomodation Type Tab
		WebElement eleAccType = driver.findElementByXPath("//span [text()='All types']");
		builder.moveToElement(eleAccType).click(eleAccType).perform();
			//Select Hotel Ony
		WebElement eleHotelOnly = driver.findElementByXPath("//input [@id = 'acc-type-filter-1' ]");
		builder.pause(3000).moveToElement(eleHotelOnly).click(eleHotelOnly).perform();
			//Select 4 Star
		WebElement ele4Star = driver.findElementByXPath("(//button[@title = '4-star hotels' ]/span)[1]");
		builder.pause(3000).moveToElement(ele4Star).click(ele4Star).pause(3000).perform();
		
		//8) Select Guest rating as Very Good
			//Click Rating Tab
		WebElement eleGuestRating = driver.findElementByXPath("(//span [@class = 'filter-value' ] ) [2]");
		wait.until(ExpectedConditions.visibilityOf(eleGuestRating));
		builder.pause(3000).moveToElement(eleGuestRating).click(eleGuestRating).perform();
			//Select Rating as Very Good
		WebElement eleRating = driver.findElementByXPath("//span [@class = 'range__text' and text() = 'Very good' ]");
		wait.until(ExpectedConditions.visibilityOf(eleRating));
		builder.pause(3000).moveToElement(eleRating).click(eleRating).perform();
		
		//9) Set Hotel Location as Agra Fort and click Done
			//Click on Hotel Location
		WebElement eleHotelLocation = driver.findElementByXPath("(//span [@class = 'filter-item__placeholder']/span)[6]");
		wait.until(ExpectedConditions.visibilityOf(eleHotelLocation));
		builder.pause(3000).moveToElement(eleHotelLocation).click(eleHotelLocation).perform();
			//Select as Agra Fort
		WebElement eleLocation = driver.findElementById("pois");
		wait.until(ExpectedConditions.visibilityOf(eleLocation));
		Select dd3 = new Select(eleLocation);
		dd3.selectByVisibleText("Agra Fort");

		//10) In more Filters, select Air conditioning, Restaurant and WiFi and click Done
			//Click on More Filter
		WebElement eleMoreFilter = driver.findElementByXPath("//span [text() = 'Select']");
		wait.until(ExpectedConditions.visibilityOf(eleMoreFilter));
		builder.pause(3000).moveToElement(eleMoreFilter).click(eleMoreFilter).perform();
			//Click on Show Filter
		WebElement eleShowFilter = driver.findElementByXPath("//input [@id = 'more-filters-search' ]");
		wait.until(ExpectedConditions.visibilityOf(eleShowFilter));
		builder.pause(3000).moveToElement(eleShowFilter).sendKeys(eleShowFilter, "Restaurant").pause(3000).perform();
			//Restaurant
		WebElement eleRestaurant = driver.findElementByXPath("(//input [@data-qa = 'filters-list-checkbox' ] ) [1]");
		wait.until(ExpectedConditions.visibilityOf(eleRestaurant));
		builder.pause(3000).moveToElement(eleRestaurant).click(eleRestaurant).perform();
		
			//Click on Show Filter and Select Air Conditioning
		wait.until(ExpectedConditions.visibilityOf(eleShowFilter));
		eleShowFilter.clear();
		builder.pause(3000).moveToElement(eleShowFilter).sendKeys(eleShowFilter, "Air Conditioning").pause(3000).perform();
		
		WebElement eleAirCon = driver.findElementByXPath("(//input [@data-qa = 'filters-list-checkbox' ] ) [1]");
		wait.until(ExpectedConditions.visibilityOf(eleAirCon));
		builder.pause(3000).moveToElement(eleAirCon).click(eleAirCon).pause(3000).perform();
			//Click on Show Filter and Select Wifi
		wait.until(ExpectedConditions.visibilityOf(eleShowFilter));
		eleShowFilter.clear();
		builder.pause(3000).moveToElement(eleShowFilter).sendKeys(eleShowFilter, "WiFi").pause(3000).perform();

		WebElement eleWifi = driver.findElementByXPath("(//input [@data-qa = 'filters-list-checkbox' ] ) [1]");
		wait.until(ExpectedConditions.visibilityOf(eleWifi));
		builder.pause(3000).moveToElement(eleWifi).click(eleWifi).pause(3000).perform();

		/*WebElement eleShowFilter = driver.findElementByXPath("//input [@id = 'more-filters-search' ]");
		wait.until(ExpectedConditions.visibilityOf(eleShowFilter));
		builder.pause(3000).moveToElement(eleShowFilter).sendKeys(eleShowFilter, "Restaurant").pause(3000).perform();*/

		
			//Click Done to update filter
		WebElement eleDone = driver.findElementById("filter-popover-done-button");
		wait.until(ExpectedConditions.visibilityOf(eleDone));
		builder.pause(3000).moveToElement(eleDone).click(eleDone).pause(3000).perform();
		
		
		//11) Sort the result as Rating & Recommended
			//Select sort by as Rating & Recommended
		WebElement eleSortBy = driver.findElementById("mf-select-sortby");
		wait.until(ExpectedConditions.visibilityOf(eleSortBy));
		Select dd4 = new Select(eleSortBy);
		dd4.selectByVisibleText("Rating & Recommended");
		
		//12) Print the Hotel name, Rating, Number of Reviews and Click View Deal
			//Print all the hotel name
		List<WebElement> eleAllHotelName = driver.findElementsByXPath("//div[@class ='item__name item__name--link']//span[@class ='item-link name__copytext']");
		System.out.println("All Hotel Name");
		for (int i = 0; i <eleAllHotelName.size(); i++) {
			String AllHotelName = eleAllHotelName.get(i).getText();
			System.out.println(AllHotelName);
		}
		
			//Print the first result hotel name
		WebElement eleHotelName = driver.findElementByXPath("(//span [ @class ='item-link name__copytext' and @data-qa = 'item-name' ] )[1]");
		System.out.println("First Hotel Name = " +eleHotelName.getText() );
			//Print the rating of first result hotel
		WebElement eleRating2 = driver.findElementByXPath("(//span[@class ='review']/span/span)[1]");
		System.out.println("Rating of first hotel = " +eleRating2.getText());
		
			//Click on view deal of first result hotel
		WebElement eleViewDeal = driver.findElementByXPath("(//button [@data-qa = 'champion-deal' ]) [1]");
		builder.pause(3000).moveToElement(eleViewDeal).click(eleViewDeal).perform();
		
			//Delete all Cookies
		driver.manage().deleteAllCookies();
		//13) Print the URL of the Page
			//Navigate to another web page
		Set<String> allWindows = driver.getWindowHandles();
		List<String> allHandles = new ArrayList<>(allWindows);
		driver.switchTo().window(allHandles.get(1));
		
		for (String eachWindow : allHandles) {
			driver.switchTo().window(eachWindow);
			if(driver.getTitle().equals("Booking.com: Hotels in Agra. Book your hotel now!")) {
				break;

			}
		}
		
			//Delete all Cookies and refresh the current page
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
			//Get the title of the current page
		System.out.println(driver.getTitle());
		
		//14) Print the Price of the Room and click Choose Your Room
			//Print all the price of the room in hotel
		List<WebElement> eleRoomPrice = driver.findElementsByXPath("//div [@class = 'bui-price-display__value prco-text-nowrap-helper prco-inline-block-maker-helper']");
		System.out.println("All Room Price");
		for (int i = 0; i < eleRoomPrice.size(); i++) {
			System.out.println(eleRoomPrice.get(i).getText());
		}
			//Click Choose Room first result
		WebElement eleChooseRoom = driver.findElementByXPath("(//span [@class = 'bui-button__text' ] )[1]");
		System.out.println(eleChooseRoom.getText());
		builder.pause(3000).click(eleChooseRoom).perform();
		
		//15) Click Reserve and I'll Reserve
		
			//Click Reserve
		WebElement eleClickReserve = driver.findElementByXPath("(//td [@class = 'submitButton' ] //span)[1]");
		builder.pause(3000).click(eleClickReserve).perform();
		
			//Print the price of room
		WebElement elePriceOfRoom = driver.findElementByXPath("//div[@class ='hprt-reservation-total-price bui-price-display__value']");
		System.out.println("The price of room is reserved is "+elePriceOfRoom.getText());
		
			//Click I'll Reserve
		WebElement eleReserve = driver.findElementByXPath("//div [@class = 'hprt-reservation-cta' ]");
		builder.pause(3000).click(eleReserve).perform();
		
		//16) Close the browser
		driver.quit();
		

	}
	
	
}
