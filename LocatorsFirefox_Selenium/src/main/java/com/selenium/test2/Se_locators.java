package com.selenium.test2;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Se_locators {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		try {
			String actualResult = "";
			String expectedResult = "$615.00";
			String userPath = System.getProperty("user.dir");
			System.out.println(userPath);
			System.setProperty("webdriver.gecko.driver",
					userPath + "/src/main/resources/FirefoxDriver/geckodriver.exe");

			driver = new FirefoxDriver();

			driver.get("http://live.guru99.com/index.php/checkout/cart");
			driver.manage().window().maximize();

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
			if (actualResult.contentEquals(expectedResult)) {
				System.out.println("Test passed. Actual result: " + actualResult + " is equal to " + expectedResult);
			} else {
				System.out
						.println("Test failed. Actual result: " + actualResult + " is not equal to " + expectedResult);
			}

			// Element not found
		} catch (NoSuchElementException ne) {

			System.err.println("WebElement not found: " + ne.getMessage());

			// Browser broken while processing, various kind of webdriver errors
		} catch (WebDriverException we) {

			System.err.println("WebElement broken: " + we.getMessage());

			//General exception
		} catch (Exception ex) {

			System.err.println(ex.getMessage());

		} finally {

			driver.quit();
		}

	}
}
