package com.selenium.test4;


import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.hamcrest.core.StringContains;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Frames_Waits_Alerts {

	private WebDriver driver;

	@Before
	public void setUp() {

		String userPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver",
				userPath + "\\src\\test\\resources\\FirefoxDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt");
		driver.manage().window().maximize();

	}

	@Test
	public void framesWaitsAlertsTest() throws InterruptedException {
		try {
			String textAlert = "TestName";
			//ignore elementnotfound exceptions until the time finish to do an action
			WebDriverWait waitVar = new WebDriverWait(driver, 10);
			// max time to find an element before executing next action
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			// to click in a button is necessary targeting to the corresponding frame
			driver.switchTo().frame("iframeResult");
			
			WebElement button = driver.findElement(By.xpath("/html/body/button"));
			// this line start explicit wait (WebDriverWait)
			waitVar.until(ExpectedConditions.elementToBeClickable(button));
			//implicit wait doesnt work from here because the line above
			button.click();

			waitVar.until(ExpectedConditions.alertIsPresent());
			// declarate one alert element to interact with one alert window
			Alert alertWindow = driver.switchTo().alert();
			alertWindow.sendKeys("TestName");
			Thread.sleep(1000);
			alertWindow.accept();

			String finalText = driver.findElement(By.id("demo")).getText();
			Thread.sleep(2000);
			System.out.println(finalText.contains("TestName") ? finalText : "Test failed");
			
			// test
			assertThat(finalText, StringContains.containsString(textAlert));
			
			// Element not found
		} catch (NoSuchElementException ne) {

			System.err.println("WebElement not found: " + ne.getMessage());

			// frame element in a webpage is not found
		} catch (NoSuchFrameException nf) {

			System.err.println("Frame not found: " + nf.getMessage());

			// alert element is not present
		} catch (NoAlertPresentException na) {

			System.err.println("Alert not found: " + na.getMessage());

			// timeout error (for implicits and explicit waits)
		} catch (TimeoutException te) {

			System.err.println("The waiting time has been exceeded (Timeout): " + te.getMessage());

			// Browser broken while processing, various kind of webdriver errors
		} catch (WebDriverException we) {

			System.err.println("WebElement broken: " + we.getMessage());

			// General exception
		} catch (Exception ex) {

			System.err.println(ex.getMessage());
		}

	}

	@After
	public void kickOff() {

		driver.quit();
	}

}
