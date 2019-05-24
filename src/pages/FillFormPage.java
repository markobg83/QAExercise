package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FillFormPage extends BasePage {
	
	private static Logger log = Logger.getLogger(FillFormPage.class);
	String filFormUrl = "https://www.ultimateqa.com/filling-out-forms/";
	
	@FindBy(xpath = "//input[@name='et_pb_contact_name_1']")
	WebElement rightNameLocator;
	
	@FindBy(xpath = "//textarea[@name='et_pb_contact_message_1']")
	WebElement rightMessageLocator;

	@FindBy(xpath = "//input[@class='input et_pb_contact_captcha']")
	WebElement captchaResultLocator;
	
	@FindBy(xpath = "//span[@class='et_pb_contact_captcha_question']")
	WebElement captchaQuestionLocator;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	WebElement rightSubmitLocator;
	
	@FindBy(xpath = "//p[contains(.,'Success')]")
	WebElement successMessageLocator;
	
	  public FillFormPage(WebDriver driver) {
	        super(driver);
	    }
	

		public FillFormPage open() {
			log.debug("Open filling out form page");	
			driver.get(filFormUrl);
			return this;
		}
		
		public void enterRightNameAndMessage(String name, String message){			
			waitForElementToBeVisible(rightNameLocator);
			rightNameLocator.sendKeys(name);
			rightMessageLocator.sendKeys(message);
		}
		
		public void enterCaptcha(String value){
			waitForElementToBeVisible(captchaResultLocator);
			captchaResultLocator.sendKeys(value);
		}

		public String getCaptchaQuestion(){
			waitForElementToBeVisible(captchaQuestionLocator);
			return captchaQuestionLocator.getText();

		}

		public void submit() {
			waitForElementToBeVisible(rightSubmitLocator);
			rightSubmitLocator.click();			
		}
		
		public int parseExpressionAndCalculate(String expression) {
			String expression1 = expression.replaceAll("\\s","");
			log.debug("Expression is: " + expression1);
			int plusIndex = expression1.indexOf("+");
			log.debug("Index of + sign is: " + plusIndex);
			int firstNum = Integer.valueOf(expression1.substring(0, plusIndex));
			log.debug("First number: " + firstNum);
			int secondNum = Integer.valueOf(expression1.substring(plusIndex, expression1.length()));
			log.debug("Second number: " + secondNum);
			return firstNum + secondNum;			
		}
		
		public String getSuccessMessage() {
			waitForElementToBeVisible(successMessageLocator);
			return successMessageLocator.getText();			
		}
}
