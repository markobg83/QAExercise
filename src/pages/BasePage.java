package pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	 protected WebDriver driver;
	 private WebDriverWait wait;
	 private static final int TIMEOUT = 15;

	private static Logger log = Logger.getLogger(BasePage.class);
	
	    public BasePage(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, TIMEOUT);
	        PageFactory.initElements(driver, this);
	        	        
	    }       
    	    	   
	    
	    protected void waitForElementToBeVisible(WebElement locator) {
			  wait.until(ExpectedConditions.visibilityOf(locator));
	    }
	    
	    protected void waitForElementToBeClickable(WebElement locator) {
			  wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }
	    
	    
	    protected boolean isElementPresent(By by){
	        try{
	            driver.findElement(by);
	            return true;
	        }
	        catch(NoSuchElementException e){
	            return false;
	        }
	    }
	    	    
	    protected boolean isElementDisplayed(By by){
	    	if(driver.findElement(by).isDisplayed() )                                                                                                         
	    	{         
	            return true; 
	    	}    
	    	else    
	    	{     
	    	   return false;    
	    	}
	    }

}
