package testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Files;
import pages.Home;
import pages.Landing;
import pages.Login;

public class DropboxTest {

	String folderName = "SeleniumTest";
	
	WebDriver driver;
	Home home;
	Login login;
	Landing landing;
	Files files;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		home = new Home(driver);
		login = new Login(driver);
		landing = new Landing(driver);
		files = new Files(driver);
	}

	@Test
	public void openDropboxHomepage() {
		String url = "https://www.dropbox.com";
		driver.get(url);
		assertTrue(driver.getCurrentUrl().startsWith(url));
	}

	@Test
	public void gotoToSignin() {
		home.signInField().click();
		// validate that login page is successfully loaded
		String actualUrl = driver.getCurrentUrl();
		assertEquals(actualUrl, "https://www.dropbox.com/login");
	}

	@Test
	@Parameters({ "dropbox.email","dropbox.password" })
	public void signin(String eMail, String password) {
		// Enter Email ID
		login.emailField().click();
		login.emailField().sendKeys(eMail);

		// Enter Password
		login.passwordField().click();
		login.passwordField().sendKeys(password);

		// Click on Sign In
		login.signInField().click();

		assertEquals(login.pageHeading().getText(), "Home");
		assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/h");
	}

	@Test
	public void uploadFile() throws InterruptedException, AWTException {
		// Upload a file from Home page
		landing.filesLink().click();

		files.uploadButton().click();
		files.fileUploadButton().click();

		// Use java Robot class to handle windows pop-up
		// IMPORTANT Virtual Keyboard configured as English (US)
		Thread.sleep(5000);
		Robot robot = new Robot();
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
		
		robot.keyPress(KeyEvent.VK_DECIMAL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyPress(KeyEvent.VK_X);
		robot.keyPress(KeyEvent.VK_T);

		robot.keyPress(KeyEvent.VK_ENTER);

		Thread.sleep(7000);
		// Validate whether file is successfully uploaded
		assertTrue(files.fileUploadPopup().isDisplayed());

		// Validate pop-up message
		String actualMessage = files.fileUploadPopupTextField().getText();
		assertTrue(actualMessage.toLowerCase().contains("uploaded")); 

		// Close the File Upload pop-up
		files.fileUploadPopupCloseButton().click();

		// Un-select uploaded File
		
//		Thread.sleep(1000);
		files.rowSelectorCheckbox("Test.txt").click();
	}

	@Test
	public void createFolder() throws InterruptedException {
		// *********************Create a Folder***************************//

		files.createNewFolderButton().click();
		files.createNewFolder(folderName);
		assertEquals(files.getNotifyBox().getAttribute("class"), "server-success");
		String folderCreatedMessage = files.getFolderCreatedMessageElement().getText();
		assertTrue(folderCreatedMessage.contains(folderName));
		//Un-select the created folder
//		Thread.sleep(5000);
		files.rowSelectorCheckbox(folderName).click();
		
	}
	
	
	@Test
	public void cleanUp() throws InterruptedException{
		
		driver.navigate().refresh();
		//Delete file
		files.rowSelectorCheckbox("Test.txt").click();
		files.getFileDeleteLink().click();
		files.getConfirmDeleteButton().click();
		
		//Delete folder
		driver.navigate().refresh();
		files.rowSelectorCheckbox(folderName).click();
		files.getFolderDeleteLink().click();
		files.getConfirmDeleteButton().click();
		
	}

	@AfterClass
	public void tearDown() {
		files.profileButton().click();
		files.signOutLink().click();
		driver.close();
	}

}
