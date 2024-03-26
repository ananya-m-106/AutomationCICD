package EndToEndApp.Pageobjects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import endToEndAutomation.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//div[text()='CVV Code ']/following::input")
	WebElement cvvCode;
	//WebElement cvvAttribute = driver.findElement(with(By.tagName("input")).below(cvvCode));
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css=".ta-results .ta-item span")
	List<WebElement> CountryAutoSuggestiveValues;
	
	@FindBy(xpath="//*[text()='Place Order ']")
	WebElement placeOrder;
	
	@FindBy(css=".hero-primary")
	WebElement confirmationmsg;
	
	
	By filteredCountryDropdownValue = By.cssSelector(".ta-results .ta-item");
	By placeOrderbutton = By.xpath("//*[text()='Place Order ']");
	String countryExtracted;
	
	
	public void userPaymentDetails(String cvv, String country) {
		cvvCode.sendKeys(cvv);
		selectCountry.sendKeys(country);
		waitForElementToBeClickable(filteredCountryDropdownValue);
		List<WebElement>searchCountriesListResult = CountryAutoSuggestiveValues;
		selectCountryFromAutoSuggestiveDropdown(searchCountriesListResult, country);
		
	}
	
	public void selectCountryFromAutoSuggestiveDropdown(List<WebElement> searchCountriesListResult, String country) {
		
		 for(int i=0; i<searchCountriesListResult.size();i++)
		  { 
			  countryExtracted = searchCountriesListResult.get(i).getText();
			  if(countryExtracted.equalsIgnoreCase(country)) 
			  {
				  	searchCountriesListResult.get(i).click(); 
			  } 
		  }
		 
		//searchCountriesListResult.stream().filter(countryList->countryList.getText().equalsIgnoreCase(country)).map(countryList -> selectCountry(countryList));
		  //countryName.click(); //forEach(countryList -> countryList.click());
	}
	
	public String placeOrder() {
		scrollDown();
		waitForElementToBeClickable(placeOrderbutton);
		  placeOrder.click();
		  String confirmationMessage = confirmationmsg.getText();
		  return confirmationMessage;
		  
	}
	 
}
