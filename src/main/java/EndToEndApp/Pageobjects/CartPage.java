package EndToEndApp.Pageobjects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import endToEndAutomation.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cart h3")
	List<WebElement> productsInCart;
	
	@FindBy(css=".subtotal button")
	WebElement checkoutButton;
	
	
	public List<WebElement> getProductsInCart() {
		return productsInCart;
		 
	}
	
	public Boolean reviewProductsInCart(String productRequired) {
		List<WebElement> cartItems = getProductsInCart();
		Boolean isPoductAddedToCart = cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productRequired));
		return isPoductAddedToCart;
		  
	}
	
	public CheckoutPage checkout() {
		checkoutButton.click();
		 CheckoutPage checkoutPage = new CheckoutPage(driver);
		 return checkoutPage;
	}
	
	
	
}
