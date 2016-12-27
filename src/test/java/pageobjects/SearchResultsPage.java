package pageobjects;

import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;

public class SearchResultsPage extends LoadableComponent<SearchResultsPage> {
	private WebDriver driver;

	@FindBy(className = "term-result-found")
	@CacheLookup
	private WebElement resultFound;

	@FindBy(xpath = "//button[contains(text(), 'Price (High to Low)')]")
	@CacheLookup
	private WebElement priceHighToLow;

	@FindBy(xpath = "//h5[contains(text(), 'Promotion')]")
	@CacheLookup
	private WebElement promotion;

	@FindBy(id = "promotions2")
	@CacheLookup
	private WebElement priceReduction;
	

	@FindBy(xpath = ".//*[@id='content']/div[2]/div[2]/div/div/div[2]/div/div[2]/div[2]/div/div[1]/div/div[4]/div/form/div[1]/div/div/button[1]")
	private WebElement add;

	private WebElement select;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		try {
			new WebDriverWait(driver, 120)
					.until(ExpectedConditions.visibilityOfElementLocated(By.className("term-result-found")));
		} catch (WebDriverException e) {
			throw new Error("Element is not visible");
		}
	}

	// Get search result title
	public String getResult() {
		return resultFound.getText();
	}

	// Sort result is rendered dynamically with JavaScript.
	// Wait for the page to load, timeout after 30 seconds.
	public SortPage sort() {
		priceHighToLow.click();
		return new SortPage(driver);
	}

	// Expand PROMOTION, and check Price Reduction
	public PromotionPage promotion() {
		promotion.click();		
		try {
			new WebDriverWait(driver, 120)
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promotions-Price.Reduction.control-label.label-promotions2")));
		} catch (WebDriverException e) {
			throw new Error("Element is not visible");
		}		
		priceReduction = driver.findElement(By.cssSelector(".promotions-Price.Reduction.control-label.label-promotions2"));
		priceReduction.click();
		return new PromotionPage(driver);
	}

	public StoreLocatorPage add() {
		System.out.println(add.getText());
		add.click();
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath("html/body/div[6]/div[2]/div[2]/div[1]/div/button")).isDisplayed();

			}
		});

		select = driver.findElement(By.xpath("html/body/div[6]/div[2]/div[2]/div[1]/div/button"));
		select.click();

		/*
		 * try {
		 * 
		 * Alert simpleAlert = driver.switchTo().alert(); String alertText =
		 * simpleAlert.getText(); System.out.println("Alert text is " +
		 * alertText); simpleAlert.accept();
		 * 
		 * } catch (NoAlertPresentException ex) { // Alert not present
		 * ex.printStackTrace(); }
		 */
		return new StoreLocatorPage(driver);

	}

	public void selectStore() {

		/*
		 * System.out.println("111111"); new WebDriverWait(driver, 20).until(new
		 * ExpectedCondition<Boolean>() { public Boolean apply(WebDriver d) {
		 * return d.findElement(By.xpath(
		 * "html/body/div[6]/div[2]/div[2]/div[1]/div/button")).isDisplayed(); }
		 * });
		 */

	}

}
