package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import static org.testng.AssertJUnit.assertEquals;

public class HomePage extends LoadableComponent<HomePage>{
	private WebDriver driver;
	static String url = "https://www.loblaws.ca/";

	@FindBy(css = ".text-search")
	//@FindBy(css = ".search-button > span + span")
	//@FindBy(css = ".icon-search + span")
	@CacheLookup
	private WebElement searchBox;

	@FindBy(id = "search-bar")
	@CacheLookup
	private WebElement searchBar;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		driver.get(url);		
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals("Welcome to Loblaws", driver.getTitle());		
	}	

	public SearchResultsPage search(String product) {
		searchBox.click();
		searchBar.clear();
		searchBar.sendKeys(product);
		searchBar.submit();
		return new SearchResultsPage(driver);
	}

}
