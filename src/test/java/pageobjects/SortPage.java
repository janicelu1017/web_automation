package pageobjects;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortPage extends LoadableComponent<SortPage> {
	private WebDriver driver;

	public SortPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}

	// The isLoaded() method will verify that the indented page is loaded.
	// By calling get() method you will jump first to the isLoaded() method, 
	// then to load() method, and finally to isLoaded() method again.
	@Override
	protected void isLoaded() throws Error {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println(js.executeScript("return document.readyState"));
		try {
			new WebDriverWait(driver, 360).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					JavascriptExecutor js = (JavascriptExecutor) d;
					return js.executeScript("return document.readyState").equals("complete");
				}
			});
		} catch (WebDriverException e) {
			throw new Error("Document state is not ready");
		}

	}

	// Get product items' price
	public ArrayList<Float> getPrice() {
		ArrayList<Float> priceList = new ArrayList<Float>();
		for (int i = 0; i < driver.findElements(By.className("reg-price")).size(); i = i + 1) {
			priceList.add(
					Float.parseFloat(driver.findElements(By.className("reg-price")).get(i).getText().substring(1)));
			System.out.println(priceList.get(i));
		}
		return priceList;
	}

	// Verify sorted items(High to Low price) are displaying properly or not
	public Boolean isDescending(ArrayList<Float> arr) {
		for (int i = 0; i < arr.size() - 1; i++) {
			if (arr.get(i) < arr.get(i + 1)) {
				return false;
			}
		}
		return true;
	}

}
