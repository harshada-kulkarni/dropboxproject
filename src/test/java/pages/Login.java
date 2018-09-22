package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

	WebDriver driver;
	By cssSelectorEmailInput = By.cssSelector("input.text-input-input");
	By cssSelectorPasswordInput = By.cssSelector("input.text-input-input.password-input");
	By cssSelectorLoginButton = By.cssSelector("button.login-button.signin-button.button-primary");
	By cssSelectorPageHeading = By.cssSelector("h1.page-header__heading");

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement emailField() {
		return driver.findElement(cssSelectorEmailInput);
	}

	public WebElement passwordField() {
		return driver.findElement(cssSelectorPasswordInput);
	}

	public WebElement signInField() {
		return driver.findElement(cssSelectorLoginButton);
	}
	
	public WebElement pageHeading() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		return wait.until(ExpectedConditions.presenceOfElementLocated(cssSelectorPageHeading));
	}
}
