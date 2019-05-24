package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pages.FillFormPage;
import pages.WebTablesPage;
import utils.DriverSetup;

public class TestCases {

	private WebDriver driver;
	private static Logger log = Logger.getLogger(FillFormPage.class);
	private FillFormPage fillFormPage;
	private WebTablesPage webTablesPage;
	private String name = "test";
	private String message = "test";
	private String captcha = "-1";
	private String successMessage = "Success";

	/**
	 * 1. Open https://www.ultimateqa.com/filling-out-forms/ 
	 * 2. Fill out the form on the right, but intentionally enter -1 as a result of the addition
	 * 3. Submit the form and confirm that the numbers have changed 
	 * 4. Close the browser
	 */
	@Test
	public void firstTestCase() {

		log.debug("firstTestCase");

		driver = DriverSetup.setUpWebBrowser("chrome");
		fillFormPage = new FillFormPage(driver);
		fillFormPage.open();

		fillFormPage.enterRightNameAndMessage(name, message);

		String firstCaptchaQuestion = fillFormPage.getCaptchaQuestion();
		fillFormPage.enterCaptcha(captcha);
		fillFormPage.submit();

		String secondCaptchaQuestion = fillFormPage.getCaptchaQuestion();

		log.debug("Check captcha question should change from: " + firstCaptchaQuestion);
		assert !firstCaptchaQuestion.equals(secondCaptchaQuestion) : "Captcha question didn't change as it should";

	}

	/**
	 * 1. Open https://www.ultimateqa.com/filling-out-forms/
	 * 2. Fill out the form on the right, fill in the correct number
	 * 3. Submit the form and confirm that a 'Success' message is displayed. 
	 * 4. Close the browser
	 */
	@Test
	public void secondTestCase() {

		log.debug("secondTestCase");

		driver = DriverSetup.setUpWebBrowser("chrome");
		fillFormPage = new FillFormPage(driver);
		fillFormPage.open();

		fillFormPage.enterRightNameAndMessage(name, message);

		String firstCaptchaQuestion = fillFormPage.getCaptchaQuestion();
		int result = fillFormPage.parseExpressionAndCalculate(firstCaptchaQuestion);
		fillFormPage.enterCaptcha(String.valueOf(result));
		fillFormPage.submit();

		String actualSuccessMessage = fillFormPage.getSuccessMessage();

		assert actualSuccessMessage.equals(successMessage) : "Success message not present";

	}
	
	/**
	 * 1. Go to the URL: http://www.way2automation.com/angularjs-protractor/webtables/
	 * 2. Click on 'Add user' to add a new user to the table
	 * 3. Populate all fields (use unique email) and click SAVE
	 * 4. Filter Email in descending order
	 * 5. Verify that Email of the newly created user is present in the Email column. 
   	 * Write a method/function that goes through Email column and when finding a desirable Email, 
     * it prints it in the Console, prints number of the row where it is located, and the test is Pass. 
     * If the desirable Email is not found, then the method should invoke the test to Fall.
	 */
	@Test
	public void thirdTestCase(){
		log.debug("thirdTestCase");
		String fn = "fn test";
		String ln = "ln test";
		String un = "un test";
		String pass = "12345pass";
		int cust = 1;
		int role = 2;
		String mail = "test" + System.currentTimeMillis() + "@verat.net";
		String phone = "01234567890";
		driver = DriverSetup.setUpWebBrowser("chrome");
		webTablesPage = new WebTablesPage(driver);
		webTablesPage.open();
		
		webTablesPage.clickAddUser();
		webTablesPage.fillUserData(fn, ln, un, pass, cust, role, mail, phone);
		webTablesPage.sortMailColumnDescending();
		
		int br = webTablesPage.checkEmailPresenceAtTable(mail);		
		assert br != -1 : "Row with email: " + mail + " is not found at table";
		
		log.debug("Mail found at row: " + br);		
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			log.debug("Closing  browser");
			 driver.quit();
		}
	}
}
