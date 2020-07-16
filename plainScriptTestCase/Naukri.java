package plainScriptTestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Naukri {
	
	@Test
	public void naukriTestCase() {
		
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
		
		
		//1) Go to https://www.naukri.com/
		driver.get("https://www.naukri.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		builder.pause(2000).perform();
		
		//2) Print the company name of pop up window and close the window
		
			//***********Method 1**************
		
		Set<String> allWindows1 = driver.getWindowHandles();
	    List<String> allHandles1 = new ArrayList<String>(allWindows1);
	    for (String eachWindow : allHandles1) {
	    	driver.switchTo().window(eachWindow);
	    	WebElement elePopup1 = driver.findElementByXPath("(//img)[1]");
		    String companyName1 = elePopup1.getAttribute("alt");
		    System.out.println(companyName1);
		    driver.close();
		}
		
		
			//****************Method2**************
		
		/*
		Set<String> allWindows = driver.getWindowHandles();
	    List<String> allHandles = new ArrayList<String>(allWindows);
	    for (String eachWindow : allHandles) {
			driver.switchTo().window(eachWindow);
			if(driver.getTitle().equals("XL Dynamics")) {
				break;
			}
		}
	    
	    WebElement elePopup1 = driver.findElementByXPath("(//img)[1]");
	    String companyName1 = elePopup1.getAttribute("alt");
	    System.out.println(companyName1);
	    driver.close();
	    
	    Set<String> allWindows2 = driver.getWindowHandles();
	    List<String> allHandles2 = new ArrayList<String>(allWindows2);
	    for (String eachWindow : allHandles2) {
			driver.switchTo().window(eachWindow);
			if(driver.getTitle().equals("Sykes")) {
				break;
			}
		}
	    
	    WebElement elePopup2 = driver.findElementByXPath("(//img)[1]");
	    String companyName2 = elePopup2.getAttribute("alt");
	    System.out.println(companyName2);
	    driver.close();
	    
	    
	    Set<String> allWindows3 = driver.getWindowHandles();
	    List<String> allHandles3 = new ArrayList<String>(allWindows3);
	    for (String eachWindow : allHandles3) {
			driver.switchTo().window(eachWindow);
			if(driver.getTitle().equals("Amazon")) {
				break;
			}
		}
	    
	    WebElement elePopup3 = driver.findElementByXPath("(//img)[1]");
	    String companyName3 = elePopup3.getAttribute("alt");
	    System.out.println(companyName3);
	    driver.close();
	    
	    driver.quit();*/
	    
	    
	    
	}

}
