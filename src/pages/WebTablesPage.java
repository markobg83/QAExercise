package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class WebTablesPage extends BasePage {
	
	private static Logger log = Logger.getLogger(FillFormPage.class);
	String webTablesUrl = "http://www.way2automation.com/angularjs-protractor/webtables/";
	
	@FindBy(xpath = "//button[@class='btn btn-link pull-right']")
	WebElement addUserButtonLocator;
	
	@FindBy(xpath = "//input[@name='FirstName']")
	WebElement firstNameLocator;
	
	@FindBy(xpath = "//input[@name='LastName']")
	WebElement lastNameLocator;
	
	@FindBy(xpath = "//input[@name='UserName']")
	WebElement userNameLocator;
	
	@FindBy(xpath = "//input[@name='Password']")
	WebElement passwordLocator;
	
	@FindBy(xpath = "//label[contains(.,'Company AAA')]")
	WebElement company1Locator;
	
	@FindBy(xpath = "//label[contains(.,'Company BBB')]")
	WebElement company2Locator;
	
	@FindBy(xpath = "//select[@name='RoleId']")
	WebElement selectRoleLocator;
	
	@FindBy(xpath = "//input[@name='Email']")
	WebElement emailLocator;
	
	@FindBy(xpath = "//input[@name='Mobilephone']")
	WebElement cellPhoneLocator;
	
	@FindBy(xpath = "//button[@class='btn btn-success']")
	WebElement saveButtonLocator;
	
	@FindBy(xpath = "//span[contains(.,'E-mail')]")
	WebElement emailColumnLocator;
	
	String xPathDescMailColumnSortLocator = "//span[@class='header-content ng-scope ng-binding sort-descent']";
	@FindBy(xpath = "//span[@class='header-content ng-scope ng-binding sort-descent']")
	WebElement descMailColumnSortLocator;
	
	String xPathAscMailColumnSortLocator = "//span[@class='header-content ng-scope ng-binding sort-ascent']";
	@FindBy(xpath = "//span[@class='header-content ng-scope ng-binding sort-ascent']")
	WebElement ascMailColumnSortLocator;

	
	  public WebTablesPage(WebDriver driver) {
	        super(driver);
	    }
	
		public WebTablesPage open() {
			log.debug("Open web tables page");	
			driver.get(webTablesUrl);
			return this;
		}
		
		public void clickAddUser(){
			log.debug("clickAddUser");	
			waitForElementToBeVisible(addUserButtonLocator);
			addUserButtonLocator.click();
		}
		
		public void fillUserData(String fn, String ln, String un, String pass, int cust, int role, String mail, String phone){
			log.debug("fillUserData");	
			waitForElementToBeVisible(firstNameLocator);
			firstNameLocator.sendKeys(fn);
			lastNameLocator.sendKeys(ln);
			userNameLocator.sendKeys(un);
			passwordLocator.sendKeys(pass);
			if (cust == 1)
				company1Locator.click();
			else
				company2Locator.click();
			
			Select dropDownRole = new Select(selectRoleLocator);			
			dropDownRole.selectByIndex(role);
			emailLocator.sendKeys(mail);
			cellPhoneLocator.sendKeys(phone);
			waitForElementToBeVisible(saveButtonLocator);
			saveButtonLocator.click();
		}
		
		public void clickEmailColumn()
		{
			emailColumnLocator.click();
		}
		
		public String getMailColumnSortOrder(){
			clickEmailColumn();
			if (isElementPresent(By.xpath(xPathDescMailColumnSortLocator))){
					return "asc";}
			else
				if (isElementPresent(By.xpath(xPathAscMailColumnSortLocator))){
						return "desc";}
				else
					return "misc";				
		}
		
		public void sortMailColumnAscending(){
			if (getMailColumnSortOrder().equals("asc")){
				log.debug("Mail column is alredy sort ascending");
			}
			else
				ascMailColumnSortLocator.click();
		}
		
		public void sortMailColumnDescending(){
			if (getMailColumnSortOrder().equals("desc")){
				log.debug("Mail column is alredy sort descending");
			}
			else
				descMailColumnSortLocator.click();
		}
		
		public int checkEmailPresenceAtTable(String mail){
		
			int rowCount=driver.findElements(By.xpath("/html/body/table/tbody/tr")).size();
			log.debug("Number of table rows: " + rowCount);
			String mailColumn;
			for (int i=1; i <= rowCount; i++){
				mailColumn = driver.findElement(By.xpath("/html/body/table/tbody/tr["+i+"]/td[7]")).getText();
				if (mailColumn.equals(mail)){
					return i;
				}
			}
			return -1;
		}

}
