package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home {
	
	WebDriver driver;
	
	public Home(WebDriver driver) {
		
		this.driver=driver;
		
	}
	
	public WebElement signInField() {
	
		WebElement signInLink = driver.findElement(By.cssSelector("a.sign-in.button-link"));
		return signInLink;
	}
	

}
