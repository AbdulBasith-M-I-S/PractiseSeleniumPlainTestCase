package plainScriptTestCase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ZoomCar {
	
	@Test
	public void zoomCarTestCase() throws IOException {
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
		driver.get("https://www.zoomcar.com/chennai");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//2. Click on Start your wonderful journey
		
		WebElement eleStart = driver.findElementByXPath("//a[@class ='search']");
		builder.pause(1000).click(eleStart).perform();
		
		//3. Select  any location under POPULAR PICK-UP POINTS and click next
		WebElement elePickUpLocation = driver.findElementByXPath("(//div[text() ='Popular Pick-up points']/following::div)[1]");
		builder.pause(2000).click(elePickUpLocation).perform();
		
		WebElement eleNext = driver.findElementByXPath("//button[@class ='proceed']");
		builder.pause(2000).click(eleNext).perform();
		
		//4. Select tomorrow's date and time as 9:00 AM as start date and time and Click Next
			//Rent Car with Start Time and Start Date (Tomorrow Date)
		
			//Pick the number of the start date
		//WebElement eleStartDate = driver.findElementByXPath("//div[@class = 'day picked low-price']/following::div[2]");
		WebElement eleStartDate = driver.findElementByXPath("(//div[@class ='days']/div)[2]");
		String startDateWithSpace = eleStartDate.getText();
		String startDate = startDateWithSpace.replaceAll("\\D", "");
		System.out.println("Start Date as "+startDate);
		builder.pause(2000).click(eleStartDate).perform();
		
		WebElement eleStartTime = driver.findElementByXPath("//div[@class ='vue-slider-always vue-slider-dot']//following::li/span[text() ='09:00']");
		String startTime = eleStartTime.getText();
		System.out.println("Start time ="+startTime);
		builder.pause(2000).click(eleStartTime).perform();
		
		wait.until(ExpectedConditions.visibilityOf(eleNext));
		builder.pause(2000).click(eleNext).perform();
		
		//5. Click on Show More and Select tomorrow's date and Select time as 6:00 PM as end date  and Click Done
			//Rent Car with End Time and End Date (Tomorrow Date as same day which is rented)
		WebElement eleShowMore = driver.findElementByXPath("//div[@class ='show-more']");
		builder.pause(2000).click(eleShowMore).perform();
		
		WebElement eleEndDate = driver.findElementByXPath("//ul[@class ='days']/li[text() ='"+startDate+"']");
		builder.pause(2000).click(eleEndDate).perform();
		
		WebElement eleEndTime = driver.findElementByXPath("//div[@class ='vue-slider-always vue-slider-dot']//following::li/span[text() ='18:00']");
		String endTime = eleEndTime.getText();
		System.out.println("End time = "+endTime);
		builder.pause(2000).click(eleEndTime).perform();
		
		//6.  Take the snapshot for PICKUP TIME and DROP OFF TIME
		long longRandomNumber = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		String stringRandomNum = Long.toString(longRandomNumber);
		System.out.println("Random No for screenshot "+stringRandomNum);
		
		WebElement elePickDropTime = driver.findElementByXPath("//div[@class ='breadcrumb']");
		File source1 = elePickDropTime.getScreenshotAs(OutputType.FILE);
		File destination1 = new File("./screenshot/PickUpAndDropTime"+stringRandomNum+".png");
		FileUtils.copyFile(source1, destination1);
		
		//7. Validate the pickup time and Drop off time are correct (as you selected) and click on Done
			//Start time and End time are correct
		if (startTime.equals("09:00") && endTime.equals("18:00")) {
			System.out.println("Start time and drop off time are correct");
		}
		
			//Click Done
		WebElement eleDone = driver.findElementByXPath("//button[@class ='proceed' or text()='Done']");
		builder.pause(2000).click(eleDone).perform();
		
		//8. Click on Price: High to Low and validate the sort order of the car price programmatically
		
		WebElement eleHighToLow = driver.findElementByXPath("(//div[@class ='sort-bar car-sort-layout']/div)[1]");
		builder.pause(2000).click(eleHighToLow).perform();
		builder.pause(2000).perform();
		
		List<WebElement> eleAllCarPrice = driver.findElementsByXPath("//div[@class ='new-price']");
		List<String> allCarPriceDesc = new ArrayList<>();
		List<String> allCarPriceDesc2 = new ArrayList<>();
		for (int i = 0; i < eleAllCarPrice.size(); i++) {
			allCarPriceDesc.add(eleAllCarPrice.get(i).getText().replaceAll("\\D", ""));
			allCarPriceDesc2.add(eleAllCarPrice.get(i).getText().replaceAll("\\D", ""));
		}
			//Sort allCarPriceDesc2 in descending order
		Collections.sort (allCarPriceDesc2, Collections.reverseOrder());
			//Check the iteration allCarPriceDesc is sorted in descending order
		if (allCarPriceDesc.equals(allCarPriceDesc2)) {
			System.out.println("Hence, Sort order of the car price is High To Low is correct");
		}
			
		//9. Print all the Car name and respective Price from High to Low ( car name -- price )
		List<WebElement> eleAllCarName = driver.findElementsByXPath("//div[@class ='details']/h3");
		String pathForCarName = "//div[@class ='details']/h3" ;
		String pathForPrice = "/following::div[@class = 'discounted-price']/div[@class = 'new-price']";
		Map<String, String> allCarNameWithPrice = new LinkedHashMap<>();
		System.out.println("Car Name with Prices below");
		for (int i = 0; i < eleAllCarName.size(); i++) {
			List<WebElement> eleAllCarName2 = driver.findElementsByXPath(pathForCarName);
			List<WebElement> eleAllCarPrice2 = driver.findElementsByXPath(pathForCarName+pathForPrice);
			String allCarNames = eleAllCarName2.get(i).getText();
			String allCarPrices = eleAllCarPrice2.get(i).getText().replaceAll("\\D", "");
			allCarNameWithPrice.put(allCarNames, allCarPrices);
			
		}
		System.out.println(allCarNameWithPrice);
		
		//10. Take snapshot of the details for the Highest price car
		WebElement eleHighPriceCarDetails = driver.findElementByXPath("(//div[@class ='car-item'])[1]");
		File source2 = eleHighPriceCarDetails.getScreenshotAs(OutputType.FILE);
		File destination2 = new File("./screenshot/HighestCarDetails"+stringRandomNum+".png");
		FileUtils.copyFile(source2, destination2);
		
		//11. Click on Know More for the Highest price car and print the rate after 45Kms
		WebElement eleKnowMore = driver.findElementByXPath("(//div[@class ='know-more-details'])[1]");
		builder.pause(2000).click(eleKnowMore).perform();
		
		WebElement elePriceAfterMaxKM = driver.findElementByXPath("(//div[@class = 'price-info'])[1]");
		String priceAfterMaxKM = elePriceAfterMaxKM.getText();
		System.out.println(priceAfterMaxKM);
		
		//12. Close the Browser	
		driver.close();
		

	}
	
	
	

}
