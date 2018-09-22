package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Files {

	WebDriver driver;

	By xpathForUploadButton = By.xpath("//div[@class='appactions-menu']/div/div/div/div/button[@class='mc-popover-trigger']");
	By xpathForFileUploadButton = By.xpath("//div[@class='mc-popover']/div[@class='primary-action-menu__popover-menu mc-popover-content mc-popover-content-attach-left mc-popover-content-position-below']/nav[@role='menu']/div[@class='mc-popover-content-scroller']/button[text()='Files']");
	By xpathForFileUploadPopup = By.xpath("//div[@class='mc-snackbar-container mc-snackbar-container--snackbar-icon']");
	By xpathForFileUploadPopupText = By.xpath("//div[@class='mc-snackbar-container mc-snackbar-container--snackbar-icon']/p[@class='mc-snackbar-title']");
	By xpathForFileUploadCloseButton = By.xpath("//div[@class='mc-snackbar-container mc-snackbar-container--snackbar-icon']/button[span='Close']");
	By xpathForRowSelectorCheckbox = By.xpath("//table[@class='mc-table brws-files-view-list']/tbody[@class='mc-table-body mc-table-body-culled']/tr[@data-filename='Test.txt']/td[@class='mc-table-cell mc-media-cell brws-checkbox-cell']");
	By cssSelectorForCreateNewFolderButton = By.cssSelector("button.mc-tertiary-link-button.secondary-action-menu__button.action-new-folder");
	By cssSelectorForFilesAndFoldersTable = By.cssSelector("tbody.mc-table-body.mc-table-body-culled");
	By cssSelectorForPageHeader = By.cssSelector("div.page-header");
	By xpathForProfileButton = By.xpath("//button[@aria-label='Account menu']");
	By cssSelectorDeleteLink = By.cssSelector("div.ue-effect-container.uee-AppActionsView-SecondaryActionMenu-text-delete");
	By cssSelectorConfirmDelete = By.cssSelector("button.mc-button.mc-button-primary");
	By xpathForFileDeleteLink=By.xpath("//div[@class='appactions-menu--buttons']/ul[@class='mc-tertiary-list secondary-action-menu']/li[8]/button");
	By xpathForFolderDeleteLink=By.xpath("//div[@class='appactions-menu--buttons']/ul[@class='mc-tertiary-list secondary-action-menu']/li[6]/button");
	By xpathForConfirmDeleteLink=By.xpath("//div[@class='mc-util-modal-actions-buttons']/button[@class='mc-button mc-button-primary']");

	public Files(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement uploadButton() {

		return driver.findElement(xpathForUploadButton);
	}

	public WebElement fileUploadButton() {
		return driver.findElement(xpathForFileUploadButton);
	}

	public WebElement fileUploadPopup() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.presenceOfElementLocated(xpathForFileUploadPopup));
	}

	public WebElement fileUploadPopupTextField() {
		return driver.findElement(xpathForFileUploadPopupText);
	}

	public WebElement fileUploadPopupCloseButton() {
		return driver.findElement(xpathForFileUploadCloseButton);
	}

	public WebElement rowSelectorCheckbox(String fileOrFolderName) {
    	return driver.findElement(By.xpath("//table[@class='mc-table brws-files-view-list']/tbody[@class='mc-table-body mc-table-body-culled']/tr[@data-filename='"+fileOrFolderName+"']/td[@class='mc-table-cell mc-media-cell brws-checkbox-cell']"));
		//return driver.findElement(xpathForRowSelectorCheckbox);
	}

	public WebElement createNewFolderButton() {
		return driver.findElement(cssSelectorForCreateNewFolderButton);

	}

	public WebElement filesAndFoldersTable() {
		return driver.findElement(cssSelectorForFilesAndFoldersTable);
	}

	public WebElement pageHeader() {
		return driver.findElement(cssSelectorForPageHeader);
	}

	public WebElement profileButton() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
		return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(xpathForProfileButton));
	}

	public WebElement signOutLink() {
		
		return driver.findElement(By.linkText("Sign out"));
	}

	public WebElement createNewFolder(String folderName) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		By firstRow = By.cssSelector("tr[aria-rowindex='1']");
		wait.until(ExpectedConditions.elementToBeClickable(firstRow));		
		WebElement newFolder = driver.findElement(firstRow);
		WebElement inputInNewFolderRow = newFolder.findElement(By.cssSelector("input"));
		inputInNewFolderRow.sendKeys(folderName);
		pageHeader().click();
		return inputInNewFolderRow;
	}
	
	public WebElement getNotifyBox() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
		return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("notify")));
	}
	
	public WebElement getFolderCreatedMessageElement() {
        return driver.findElement(By.id("notify-msg"));
	}

	public WebElement getLinkToFileOrFolder(String fileOrFolderName) {
		WebElement row = driver.findElement(By.cssSelector("tr[data-filename='" + fileOrFolderName + "']"));
		WebElement checkbox = row.findElement(By.cssSelector("input[type='checkbox']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
		return checkbox;
	}
	
	public WebElement getFileDeleteLink() {
//		WebElement deletelink = driver.findElement(cssSelectorDeleteLink);
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deletelink);
		WebElement deleteLink=driver.findElement(xpathForFileDeleteLink);
		
		return deleteLink;
	}
	
	public WebElement getFolderDeleteLink() {
		WebElement deleteLink=driver.findElement(xpathForFolderDeleteLink);
		return deleteLink;
	}	
	
	public WebElement getConfirmDeleteButton() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 1);
		return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(xpathForConfirmDeleteLink));
	}
	
}
