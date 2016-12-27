package pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class StoreLocatorPage {
  
	private WebDriver driver;
	@FindBy(id = "enter-new-search-term")
	private WebElement searchTerm;
	public StoreLocatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		

	}
	
	public void selectStore(String postalCode) {
		searchTerm.clear();
		searchTerm.sendKeys(postalCode);
		searchTerm.submit();		
	}
	
	public void shop() {
		driver.findElement(By.xpath("//span[contains(text(), 'Shop')]")).click();
	}
	
	
	
}
