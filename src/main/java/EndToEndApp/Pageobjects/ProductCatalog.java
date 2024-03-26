package EndToEndApp.Pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import endToEndAutomation.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{

	WebDriver driver;
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".col-lg-4")
	List<WebElement> itemsDisplayed;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsdisplayed = By.cssSelector(".col-lg-4");
	By productName = By.cssSelector("h5 b");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toasterMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> productsDisplayed() {
		waitForElementToAppear(productsdisplayed);
		return itemsDisplayed;
	}
	
	public WebElement getProduct(String productRequired) {
		WebElement product1 = productsDisplayed().stream().filter(product-> product.findElement(productName).getText().equalsIgnoreCase(productRequired)).findFirst().orElse(null);
		return product1;
	}
	
	public void addProductToCart(String productRequired) throws InterruptedException {
		getProduct(productRequired).findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementToAppear(toasterMessage);
		Thread.sleep(1000);
		//waitForElementToDisapper(spinner);
		
	}
	
	
	
}
