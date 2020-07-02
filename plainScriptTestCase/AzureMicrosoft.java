package plainScriptTestCase;

import java.awt.RenderingHints.Key;
import java.io.File;
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

public class AzureMicrosoft {
	
	@Test
	public void azureMicrosoftTestCase() {
		
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
		
		//1) Go to https://azure.microsoft.com/en-in/
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//2) Click on Pricing
		
		WebElement elePricing = driver.findElementByXPath("//a[@id ='navigation-pricing']");
		builder.click(elePricing).perform();
		
		//3) Click on Pricing Calculator
		
		WebElement elePricingCalculator = driver.findElementByXPath("(//a[@data-bi-id ='global-navigation-secondarynav-clicked-topmenu'])[2]");
		builder.click(elePricingCalculator).perform();
		
		//4) Click on Containers
		
		WebElement eleContainer = driver.findElementByXPath("//button[@data-event-property ='containers']");
		builder.click(eleContainer).perform();
		
		//5) Select Container Instances
			//Click on Container Instances
		WebElement eleContainerInstances = driver.findElementByXPath("(//span[text() ='Container Instances'])[3]");
		builder.click(eleContainerInstances).perform();
		
		//6) Click on Container Instance Added View
			//Click on container instance view popup
		WebElement eleViewContainer = driver.findElementByXPath("//button[@id ='new-module-loc']");
		wait.until(ExpectedConditions.visibilityOf(eleViewContainer));
		builder.pause(2000).click(eleViewContainer).perform();
		
		//7) Select Region as "South India"
			//Change Region to South India
		WebElement eleRegion = driver.findElementByXPath("(//select[@class ='select'])[1]");
		Select dropdown1 = new Select(eleRegion);
		dropdown1.selectByVisibleText("South India");
		
		//8) Set the Duration as 180000 seconds
			//Default value is 10. Change into to 180000
		WebElement eleDuration = driver.findElementByXPath("//input[@name ='seconds']");
		builder.sendKeys(eleDuration, Keys.ARROW_RIGHT, Keys.BACK_SPACE, Keys.BACK_SPACE).pause(2000).perform();;
		builder.pause(2000).sendKeys(eleDuration,"18000").perform();
		
		//9) Select the Memory as 4GB
			//Select 4GB
		WebElement eleMemory = driver.findElementByXPath("//select[@name ='memory']");
		Select dropdown2 = new Select(eleMemory);
		dropdown2.selectByVisibleText("4 GB");
		
		//10) Enable SHOW DEV/TEST PRICING
			//Click on Enabel Show/Dev Princing
		WebElement eleEnableShowDevTest1 = driver.findElementByXPath("//button[@name ='devTestSelected']");
		builder.pause(2000).click(eleEnableShowDevTest1).perform();
		
		//11) Select Indian Rupee  as currency
			//Select Indian Rupee for Container
		WebElement eleCurrency = driver.findElementByXPath("//select[@class ='select currency-dropdown']");
		Select dropdown3 = new Select(eleCurrency);
		dropdown3.selectByVisibleText("Indian Rupee (₹)");
		
		//12) Print the Estimated monthly price
		
		WebElement eleEstimatedMonthlyPrice = driver.findElementByXPath("(//span[@class = 'numeric'])[6]");
		System.out.println(eleEstimatedMonthlyPrice.getText());
		
		//13) Click on Export to download the estimate as excel
			//Export Excel Container file
		WebElement eleExportExcelForContainer = driver.findElementByXPath("//button[@class ='calculator-button button-transparent export-button']");
		builder.pause(2000).click(eleExportExcelForContainer).perform();
		
		//14) Verify the downloded file in the local folder
			//Verify the Excel Container file is exist or not
		builder.pause(5000).perform();
		boolean flag;
			//Path of downloaded file
        String dirPath = "C:\\Users\\SUNSHINE PRINTERS\\Downloads"; 
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files.length == 0 || files == null) {
            System.out.println("The directory is empty");
            flag = false;
        } else {
            for (File listFile : files) {
                if (listFile.getName().contains("ExportedEstimate.xlsx")) {
                    System.out.println("ExportedEstimate.xlsx" + " is present");
                    break;
                }
                flag = true;
            }
        }
        
		//15) Navigate to Example Scenarios and Select CI/CD for Containers
			//Click on Example Scenario
        WebElement eleExampleScenario = driver.findElementByXPath("//a[@class ='text-slate01' and text() ='Example Scenarios']");
		builder.click(eleExampleScenario).perform();
			//Click on CI/CD Container
		WebElement eleCICDContainer = driver.findElementByXPath("//span[@class ='service-heading' and text() = 'CI/CD for Containers']");
		builder.click(eleCICDContainer).perform();
		
		//16) Click Add to Estimate
			//Add To Estimate
		WebElement eleAddToEstimate = driver.findElementByXPath("//button[@data-event = 'area-pricing-calculator-added-solutionarchitecture']");
		builder.click(eleAddToEstimate).perform();
		
		//17) Change the Currency as Indian Rupee
			//Change the currency to Indian Rupee
		builder.pause(5000).perform();
		WebElement eleCurrencyForContainer = driver.findElementByXPath("//select[@class ='select currency-dropdown']");
		Select dropdown4 = new Select(eleCurrencyForContainer);
		dropdown4.selectByVisibleText("Indian Rupee (₹)");
		
		//18) Enable SHOW DEV/TEST PRICING
			//Click on Show DEV/TEST Pricing
		WebElement eleEnableShowDevTest2 = driver.findElementByXPath("//button[@name ='devTestSelected']");
		builder.click(eleEnableShowDevTest2).perform();
		
		
		//19) Export the Estimate For CI/CD Container
			//Export Excel CI/CD Container
		WebElement eleExportExcelCICDEstimate = driver.findElementByXPath("//button[@class ='calculator-button button-transparent export-button']");
		builder.pause(2000).click(eleExportExcelCICDEstimate).perform();
		
		//20) Verify the downloded file in the local folder
			//Verify Excel CI/Cd Container file is exist or not
		builder.pause(5000).perform();
		boolean flag2;
			//Path of downloaded file
        String dirPath2 = "C:\\Users\\SUNSHINE PRINTERS\\Downloads"; 
        File dir2 = new File(dirPath);
        File[] files2 = dir.listFiles();
        if (files2.length == 0 || files2 == null) {
            System.out.println("The directory is empty");
            flag2 = false;
        } else {
            for (File listFile : files2) {
                if (listFile.getName().contains("ExportedEstimate.xlsx")) {
                    System.out.println("ExportedEstimate.xlsx" + " is present");
                    break;
                }
                flag2 = true;
            }
        }
		
        //21) Close the Browser
        driver.close();
		
	}
}
