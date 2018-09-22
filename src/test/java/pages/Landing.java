package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Landing {

	WebDriver driver;
	By cssSelectorForFiles = By.cssSelector("a#files.maestro-nav__product");

	public Landing(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement filesLink() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		return wait.until(ExpectedConditions.presenceOfElementLocated(cssSelectorForFiles));

	}

}
