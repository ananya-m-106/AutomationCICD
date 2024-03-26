package EndToEndApp.practice;

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

import EndToEndApp.Pageobjects.LandingPage;

public class StandaloneAppFunctionality {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("ramusfkacc1@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Durga@12");
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		
		List<WebElement> itemsDisplayed = driver.findElements(By.cssSelector(".col-lg-4"));
		String itemRequired = "ZARA COAT 3";
		
		/* Implementing with for Loop
		 * for(WebElement i : itemsDisplayed) {
		 * if(i.findElement(By.cssSelector("h5 b")).getText().equalsIgnoreCase(
		 * itemRequired)) {
		 * i.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		 * break; } }
		 */
		
		//With Java Stream
		WebElement product1 = itemsDisplayed.stream().filter(product-> product.findElement(By.cssSelector("h5 b")).getText().equalsIgnoreCase(itemRequired)).findFirst().orElse(null);
		product1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[contains(@routerlink, 'cart')]")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart h3"));
		Assert.assertTrue(cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(itemRequired)));
		driver.findElement(By.cssSelector(".subtotal button")).click();
		
		WebElement cvvCode = driver.findElement(By.className("numberCircle"));
		driver.findElement(with(By.tagName("input")).below(cvvCode)).sendKeys("000");
		String country = "India";
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(country);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ta-results .ta-item")));
		List<WebElement> searchCountriesListResult = driver.findElements(By.cssSelector(".ta-results .ta-item span"));
		
		  String countryExtracted; for(int i=0; i<searchCountriesListResult.size();i++)
		  { countryExtracted = searchCountriesListResult.get(i).getText();
		  if(countryExtracted.equalsIgnoreCase(country)) {
		  searchCountriesListResult.get(i).click(); } }
		 
		//searchCountriesListResult.stream().filter(countryList->countryList.getText().equalsIgnoreCase(country)).map(countryList -> selectCountry(countryList));
		//countryName.click();
		//forEach(countryList -> countryList.click());
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Place Order ']")));
		driver.findElement(By.xpath("//*[text()='Place Order ']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
		
		
		
		
		
		
		
	}

	public static boolean selectCountry(WebElement countryList) {
		countryList.click();
		return true;
		
	}
}
