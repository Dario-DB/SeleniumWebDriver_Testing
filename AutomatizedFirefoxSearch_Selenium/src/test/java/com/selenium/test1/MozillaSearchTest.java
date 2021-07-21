package com.selenium.test1;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MozillaSearchTest {

	//driver de navegador es lo que permite crear una instancia para simular una acción en navegador

	private WebDriver driver;
	
	@Before
	public void setUp() {
		String userPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", userPath + "\\src\\test\\resources\\FirefoxDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize(); 
		driver.get("https://www.google.com/");//http get request
		

	}
	
	

	
	@Test
	public void automatizedSearch() throws InterruptedException {	
	driver.findElement(By.id("L2AGLb")).click(); //aceptar condiciones
	WebElement searchbox = driver.findElement(By.name("q"));; //q = elemento de caja de búsqueda
	searchbox.clear(); //limpiar cualquier texto en caja de busqueda
	searchbox.sendKeys("Youtube"); //busqueda a introducir
	searchbox.submit(); //hacer busqueda
	
	//rango de tiempo para cargar la pagina y pasar test -doesnt work for me-
	//driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	
	//esperar 5 segundos. Si no se pone un implicitlyWait o thread.sleep , el test
	//puede dar un falso negativo porque el tiempo que tarda en 
	//correr el test es menor que el tiempo necesario para hacer la busqueda
	Thread.sleep(5000);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//comprobar que ese sea el titulo en la ventana del browser
	assertEquals("Youtube - Buscar con Google", driver.getTitle()); 
	}
	
	@After
	public void kickOff() {
		
		driver.quit();
	}
	
}
