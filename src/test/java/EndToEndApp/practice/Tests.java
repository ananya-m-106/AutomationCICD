package EndToEndApp.practice;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EndToEndApp.Pageobjects.CartPage;
import EndToEndApp.Pageobjects.CheckoutPage;
import EndToEndApp.Pageobjects.LandingPage;
import EndToEndApp.Pageobjects.OrdersPage;
import EndToEndApp.Pageobjects.ProductCatalog;
import EndToEndApp.TestComponents.BaseTest;

public class Tests extends BaseTest{

	String productRequired = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups= {"ErrorHandling","Regression"})
	public void placeOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalog productCatalog = landingPage.loginToApp(input.get("email"),input.get("password"));
		List<WebElement> itemsDisplayed = productCatalog.productsDisplayed();
		
		productCatalog.addProductToCart(input.get("Requiredproduct"));
		CartPage cartPage = productCatalog.navigateToCart();
		Boolean isPoductAddedToCart = cartPage.reviewProductsInCart(input.get("Requiredproduct"));
		Assert.assertTrue(isPoductAddedToCart);
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.userPaymentDetails("000", "India");
		String confirmationMessage = checkoutPage.placeOrder();
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
		
	}

	/*
	 * public static boolean selectCountry(WebElement countryList) {
	 * countryList.click(); return true;
	 * 
	 * }
	 */
	@Test(dataProvider= "getData", dependsOnMethods= {"placeOrder"})
	public void orderHistory(HashMap<String, String> input) {
		landingPage.loginToApp(input.get("email"), input.get("password"));
		OrdersPage orderPage = landingPage.navigateToOrdersPage();
		Assert.assertTrue(orderPage.getOrderHistoryDetails(input.get("Requiredproduct")));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\EndToEndApp\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0) },{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"ramusfkacc1@gmail.com", "Durga@12","ZARA COAT 3" },{"ramusfk1acc@gmail.com", "Durga$12","ADIDAS ORIGINAL"}};
//	OR
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "ramusfkacc1@gmail.com");
//	map.put("password", "Durga@12");
//	map.put("Requiredproduct", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "ramusfk1acc@gmail.com");
//	map1.put("password", "Durga$12");
//	map1.put("Requiredproduct", "ADIDAS ORIGINAL");
	
//	}
	
	
}
