package com.selenium.test2;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Se_locators {

	static WebDriver driver;

	@Before
	public void setUp() {
		
		
		try {
		
		String userPath = System.getProperty("user.dir");
		System.out.println(userPath);
		System.setProperty("webdriver.gecko.driver",
				userPath + "/src/main/resources/FirefoxDriver/geckodriver.exe");

		driver = new FirefoxDriver();

		driver.get("http://live.guru99.com/index.php/checkout/cart");
		driver.manage().window().maximize();
	} catch (NoSuchElementException ne) {

		System.err.println("WebElement not found: " + ne.getMessage());

		// Browser broken while processing, various kind of webdriver errors
	} catch (WebDriverException we) {

		System.err.println("WebElement broken: " + we.getMessage());

		//General exception
	} catch (Exception ex) {

		System.err.println(ex.getMessage());

	}
	}
	
	@Test
	public void locatorsTest() throws InterruptedException {	
		
		String actualResult = "";
		String expectedResult = "$615.00";
		
		try {
			// TV SECTION
			WebElement linkTV = driver.findElement(By.linkText("TV"));
			linkTV.click();
			// ADD TO CART
			WebElement btnAdd = driver.findElement(By
					.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/button/span/span"));
			btnAdd.click();
			// PRICE TEXT
			WebElement subtotal_price = driver
					.findElement(By.cssSelector(".product-cart-price > span:nth-child(1) > span:nth-child(1)"));
			actualResult = subtotal_price.getText();
			Thread.sleep(3000);

			// TEST
			assertEquals(actualResult, expectedResult); 

			// Element not found
		} catch (NoSuchElementException ne) {

			System.err.println("WebElement not found: " + ne.getMessage());

			// Browser broken while processing, various kind of webdriver errors
		} catch (WebDriverException we) {

			System.err.println("WebElement broken: " + we.getMessage());

			//General exception
		} catch (Exception ex) {

			System.err.println(ex.getMessage());

		}
		
		

	}
	
	@After
	public void kickOff() {
		
		driver.quit();
	}
}
