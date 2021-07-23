package com.selenium.test3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.Select;

public class FillingForm {

	static WebDriver driver;

	public static void main(String[] args) {

		String userPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", userPath + "/src/main/resources/FirefoxDriver/geckodriver.exe");

		String expectedResult = "User1 User1_lastname";
		String actualResult = "";
		
		driver = new FirefoxDriver();
		driver.get("https://demoqa.com/");
		driver.manage().window().maximize();

		try {
			//link to section page
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div[2]")).click();
			Thread.sleep(2000);
			//link to go to form
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div/div/div[2]/div")).click();
			Thread.sleep(2000);
			
			WebElement txtFirstName = driver.findElement(By.id("firstName"));
			txtFirstName.sendKeys("User1");

			driver.findElement(By.id("lastName")).sendKeys("User1_lastname");
			driver.findElement(By.id("userEmail")).sendKeys("user1@gmail.com");
			

			// male gender
			driver.findElement(By.cssSelector("div.custom-radio:nth-child(1)")).click();
			
			//user number
			driver.findElement(By.id("userNumber")).sendKeys("1234567890");

			// input with calendar, selects elements and click on day of month MM and year
			// YYYY
			driver.findElement(By.id("dateOfBirthInput")).click();
			Select setpMonth = new Select(driver.findElement(By.xpath(
					"/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select")));
			setpMonth.selectByVisibleText("June");

			Select setYear = new Select(driver.findElement(By.xpath(
					"/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select")));
			setYear.selectByValue("1995");
			
			//click on day 29
			driver.findElement(By.
					xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div[5]/div[5]"))
					.click();
			Thread.sleep(1000);

			// hobbies sports and reading
			WebElement hob1 = driver.findElement(By.xpath("//*[@id=\"hobbies-checkbox-1\"]"));
			WebElement hob2 = driver.findElement(By.xpath("//*[@id=\"hobbies-checkbox-2\"]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			// must be clicked with JS script because of web elements type
			executor.executeScript("arguments[0].click();", hob1);
			Thread.sleep(1000);
			executor.executeScript("arguments[0].click();", hob2);
			Thread.sleep(1000);

			// select state and city. There is no select element, it is a dynamic event
			// (react), select object cant be used.
			WebElement selectState = driver.findElement(
					By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[10]/div[2]/div/div"));
			executor.executeScript("arguments[0].scrollIntoView();", selectState);
			Thread.sleep(1000);
			selectState.click();
			Thread.sleep(1000);

			WebElement selectOneOState = driver.findElement(By.xpath("//*[@id=\"react-select-3-input\"]"));
			selectOneOState.sendKeys("NCR");
			selectOneOState.sendKeys(Keys.ENTER);

			WebElement selectCity = driver.findElement(
					By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[10]/div[3]/div/div"));
			Thread.sleep(1000);
			selectCity.click();
			Thread.sleep(1000);

			WebElement selectOneCity = driver.findElement(By.xpath("//*[@id=\"react-select-4-input\"]"));
			selectOneCity.sendKeys("Gurgaon");
			selectOneCity.sendKeys(Keys.ENTER);

			Thread.sleep(2000);

			selectOneCity.submit();
			Thread.sleep(5000);
			actualResult = driver
					.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)"))
					.getText();

			if (expectedResult.contentEquals(actualResult)) {
				System.out.println("Test passed: " + expectedResult + " is equal to " + actualResult);
			} else {

				System.err.println("Test failed: " + expectedResult + " is not equal to " + actualResult);
			}
				//web element not found in webpage
		} catch (NoSuchElementException ne) {

			System.err.println("WebElement not found: " + ne.getMessage());

			//  webdriver errors
		} catch (WebDriverException we) {

			System.err.println("WebElement broken: " + we.getMessage());

			// General exception
		} catch (Exception ex) {

			System.err.println(ex.getMessage());

		} finally {

			driver.quit();
		}

	}
}