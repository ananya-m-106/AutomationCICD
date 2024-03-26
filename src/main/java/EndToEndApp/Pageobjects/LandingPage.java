package EndToEndApp.Pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import endToEndAutomation.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userName;
	@FindBy(xpath="//input[@type='password']")
	WebElement passwordField;
	@FindBy(css="input[value='Login']")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorToasterMessage;
	

	public void browserLaunch() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalog loginToApp(String username, String password) {
		userName.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(ErrorToasterMessage);
		return ErrorToasterMessage.getText();
	}
}
