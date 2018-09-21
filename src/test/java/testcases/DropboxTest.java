package testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.Home;
import pages.Login;

public class DropboxTest {

	@Test()
	public void openDropBox() throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Harsha/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.dropbox.com");
		Home home = new Home(driver);
		home.getSignInLink().click();
		//validate that login page is successfully loaded
		
		String actualUrl=driver.getCurrentUrl();
		assertEquals(actualUrl, "https://www.dropbox.com/login");
				
			
		Login login = new Login(driver);
		// Enter Email ID
		login.emailField().click();
		login.emailField().sendKeys("");

		// Enter Password
		login.passwordField().click();
		login.passwordField().sendKeys("");
		
		//Click on Sign In
		login.signInField().click();
		
		//Now log out. !!! Work on removing the thread sleep
		
//		Thread.sleep(5000);
//		//WebElement profileButton = driver.findElement(By.cssSelector("button.mc-popover-trigger"));
//		WebElement profileButton = driver.findElement(By.xpath("//button[@aria-label='Account menu']"));
//		profileButton.click();
//		Thread.sleep(5000);		
////		WebElement signOutLink = driver.findElement(By.cssSelector("a.mc-account-menu-item"));
//		WebElement signOutLink = driver.findElement(By.linkText("Sign out"));
//		signOutLink.click();
		
		//Upload a file - From landing page
		
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//div[@class='appactions-menu']/div/div/div/div/button[@class='mc-popover-trigger']")).click();
//		WebElement fileUpload= driver.findElement(By.cssSelector("button.action-upload-files.mc-popover-content-item"));
//		fileUpload.click();
		
        //Upload a file from Home page
		//Thread.sleep(5000);
		
		By cssSelectorForFiles = By.cssSelector("a#files.maestro-nav__product");
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(cssSelectorForFiles));
		
		driver.findElement(cssSelectorForFiles).click();
		driver.findElement(By.xpath("//div[@class='appactions-menu']/div/div/div/div/button[@class='mc-popover-trigger']")).click();		
		WebElement fileUpload= driver.findElement(By.xpath("//div[@class='mc-popover']/div[@class='primary-action-menu__popover-menu mc-popover-content mc-popover-content-attach-left mc-popover-content-position-below']/nav[@role='menu']/div[@class='mc-popover-content-scroller']/button[text()='Files']"));	
		fileUpload.click();
		
		//Use java Robot class to handle windows pop-up
		//IMPORTANT Virtual Keyboard configured as English (US)
		Thread.sleep(5000);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_C);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_SEMICOLON);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_BACK_SLASH);

		robot.keyPress(KeyEvent.VK_S);
		robot.keyPress(KeyEvent.VK_P);
		robot.keyPress(KeyEvent.VK_BACK_SLASH);
		
		robot.keyPress(KeyEvent.VK_T);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyPress(KeyEvent.VK_T);
		
		
		robot.keyPress(KeyEvent.VK_ENTER);
		
		//Validate whether file is successfully uploaded
		Thread.sleep(7000);
		
        assertTrue(driver.findElement(By.xpath("//div[@class='mc-snackbar-container mc-snackbar-container--snackbar-icon']")).isDisplayed()); 
        String actualMessage = driver.findElement(By.xpath("//div[@class='mc-snackbar-container mc-snackbar-container--snackbar-icon']/p[@class='mc-snackbar-title']")).getText(); 
        assertTrue(actualMessage.contains("Uploaded"));
        
        
        //Close the File Upload pop-up
        driver.findElement(By.xpath("//div[@class='mc-snackbar-container mc-snackbar-container--snackbar-icon']/button[span='Close']")).click();
        
        //Un-select uploaded File
        driver.findElement(By.xpath("//table[@class='mc-table brws-files-view-list']/tbody[@class='mc-table-body mc-table-body-culled']/tr[@data-filename='Test.txt']/td[@class='mc-table-cell mc-media-cell brws-checkbox-cell']")).click();;
        
        //*********************Create a Folder***************************//
        
//        driver.findElement(By.cssSelector("button.mc-tertiary-link-button.secondary-action-menu__button.action-new-folder")).click();
        
        String folderName = "SeleniumTest";
        
        //WebElement newFolder = driver.findElement(By.xpath("//table[@class='mc-table brws-files-view-list']/tbody[@class='mc-table-body mc-table-body-culled']/tr[@aria-rowindex='1']/td[@class='mc-table-cell mc-media-cell']/div[@class='brws-file-name-container']/div/div/p/span/a/div/span"));
        //newFolder.sendKeys("SeleniumTest");
        driver.findElement(By.cssSelector("button.mc-tertiary-link-button.secondary-action-menu__button.action-new-folder")).click(); 
        WebElement tbody = driver.findElement(By.cssSelector("tbody.mc-table-body.mc-table-body-culled"));
        WebElement newFolder = tbody.findElement(By.cssSelector("tr[aria-rowindex='1']"));
        newFolder.findElement(By.cssSelector("input")).sendKeys(folderName);
        driver.findElement(By.cssSelector("div.page-header")).click();
        
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("notify-msg")));
        WebElement folderCreatedMessage = driver.findElement(By.id("notify-msg"));
        
        assertTrue(folderCreatedMessage.getText().contains(folderName));
        
		// driver.quit();

	}

}