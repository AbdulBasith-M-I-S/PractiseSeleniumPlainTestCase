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

public class SuperCalculator {
	
	@Test
	public void superCalculatorTestCase() {
		
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
		
		//1) Go to https://juliemr.github.io/protractor-demo/
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//2) Enter First Number
		WebElement eleEnterFirstNum = driver.findElementByXPath("//form[@class ='form-inline ng-pristine ng-valid']/input[1]");
		wait.until(ExpectedConditions.visibilityOf(eleEnterFirstNum));
		builder.pause(2000).click(eleEnterFirstNum).sendKeys(eleEnterFirstNum, "25").perform();
		
		//3) Select the operator to do calculation
		WebElement eleSelectOperator = driver.findElementByXPath("//form[@class ='form-inline ng-valid ng-dirty ng-valid-parse']/select");
		wait.until(ExpectedConditions.visibilityOf(eleSelectOperator));
		Select dropDown = new Select(eleSelectOperator);
		dropDown.selectByValue("MULTIPLICATION");
		
		//4) Enter Second Number
		WebElement eleEnterSecondNum = driver.findElementByXPath("//form[@class ='form-inline ng-valid ng-dirty ng-valid-parse']/input[2]");
		wait.until(ExpectedConditions.visibilityOf(eleEnterSecondNum));
		builder.pause(2000).click(eleEnterSecondNum).sendKeys(eleEnterSecondNum, "5").perform();
		
		//5) Click on Go
		WebElement eleGo = driver.findElementByXPath("//button[@id ='gobutton']");
		wait.until(ExpectedConditions.visibilityOf(eleGo));
		builder.pause(2000).click(eleGo).perform();
		
		//6) Get the loading dot
		WebElement eleLoadingDot = driver.findElementByXPath("//h2[@class ='ng-binding']");
		wait.until(ExpectedConditions.textToBePresentInElement(eleLoadingDot, "."));
		System.out.println(eleLoadingDot.getText());
		wait.until(ExpectedConditions.textToBePresentInElement(eleLoadingDot, ". ."));
		System.out.println(eleLoadingDot.getText());
		wait.until(ExpectedConditions.textToBePresentInElement(eleLoadingDot, ". . ."));
		System.out.println(eleLoadingDot.getText());
		wait.until(ExpectedConditions.textToBePresentInElement(eleLoadingDot, ". . . ."));
		System.out.println(eleLoadingDot.getText());
		wait.until(ExpectedConditions.textToBePresentInElement(eleLoadingDot, ". . . . ."));
		System.out.println(eleLoadingDot.getText());
		wait.until(ExpectedConditions.textToBePresentInElement(eleLoadingDot, ". . . . . ."));
		System.out.println(eleLoadingDot.getText());
		
		
		//7) Get the final result
		builder.pause(2000).perform();
		System.out.println(eleLoadingDot.getText());
		
		
		//8) Close the browser
		driver.close();
		
		
		
		
	}

}
