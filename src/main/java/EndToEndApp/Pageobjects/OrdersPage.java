package EndToEndApp.Pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import endToEndAutomation.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{

	public WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="tbody tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean getOrderHistoryDetails(String productRequired) {
		
		Boolean match = productNames.stream().anyMatch(productName->productName.getText().equalsIgnoreCase(productRequired));
		return match;
		
	}
}
