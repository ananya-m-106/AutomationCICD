package endToEndAutomation.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import EndToEndApp.Pageobjects.CartPage;
import EndToEndApp.Pageobjects.OrdersPage;


public class AbstractComponents {

	WebDriver driver;
	
	
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[contains(@routerlink, 'cart')]")
	WebElement cartIcon;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement OrderHistoryButton;

	public void waitForElementToAppear(By FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
		
	}
	
	public void waitForElementToDisapper(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(FindBy));
	}
	
	public void waitForElementToBeClickable(By FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.elementToBeClickable(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
		
	}
	
	public CartPage navigateToCart() {
		cartIcon.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage navigateToOrdersPage() {
		OrderHistoryButton.click();
		OrdersPage orderPage = new OrdersPage(driver);
		return orderPage;
	}
	
	
	public void scrollDown() {
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		  js.executeScript("window.scrollBy(0,700)");
	}
	
}
