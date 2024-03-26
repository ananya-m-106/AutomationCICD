package EndToEndApp.practice;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import EndToEndApp.Pageobjects.CartPage;
import EndToEndApp.Pageobjects.CheckoutPage;
import EndToEndApp.Pageobjects.LandingPage;
import EndToEndApp.Pageobjects.ProductCatalog;
import EndToEndApp.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer= EndToEndApp.TestComponents.Retry.class)
	public void loginpageErrorValidation() throws IOException {
		ProductCatalog productCatalog = landingPage.loginToApp("ramusfkacc@gmail.com", "Durga@12");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test
	public void productPageErrorValidation() throws IOException, InterruptedException {
		ProductCatalog productCatalog = landingPage.loginToApp("ramusfkacc1@gmail.com", "Durga@12");
		List<WebElement> itemsDisplayed = productCatalog.productsDisplayed();
		String productRequired = "ZARA COAT 3";
		productCatalog.addProductToCart(productRequired);
		CartPage cartPage = productCatalog.navigateToCart();
		Boolean isPoductAddedToCart = cartPage.reviewProductsInCart("ZARA COAT");
		Assert.assertFalse(isPoductAddedToCart);
	}

}
