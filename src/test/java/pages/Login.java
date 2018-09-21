package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

	WebDriver driver;

	public Login(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement emailField() {
		WebElement eMailId = driver.findElement(By.cssSelector("input.text-input-input"));
		return eMailId;
	}

	public WebElement passwordField() {
		WebElement password = driver.findElement(By.cssSelector("input.text-input-input.password-input"));
		return password;
	}

	public WebElement signInField() {
		return driver.findElement(By.cssSelector("button.login-button.signin-button.button-primary"));
	}

}
