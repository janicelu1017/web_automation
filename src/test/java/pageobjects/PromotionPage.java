package pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.math.BigDecimal;
import java.math.MathContext;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PromotionPage extends LoadableComponent<PromotionPage> {
	private WebDriver driver;

	public PromotionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	// Verify that the indented page is loaded.
	@Override
	protected void isLoaded() throws Error {
		try {
			new WebDriverWait(driver, 240).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return driver.findElements(By.cssSelector(".deal-type")).get(0).getText().contains("SAVE $");
				}
			});
		} catch (WebDriverException e) {
			throw new Error("Element is not filtered");
		}

	}

	// Get the amount on the badge
	public ArrayList<BigDecimal> getBadge() {
		ArrayList<BigDecimal> badgeList = new ArrayList<BigDecimal>();
		for (int i = 0; i < driver.findElements(By.cssSelector(".deal-type")).size(); i = i + 1) {
			if (driver.findElements(By.cssSelector(".deal-type")).get(i).getText().contains("SAVE $")) {
				badgeList.add(new BigDecimal(
						driver.findElements(By.cssSelector(".deal-type")).get(i).getText().substring(6)));
			}
		}
		return badgeList;
	}

	// Get the regular price
	public ArrayList<BigDecimal> getRegPrice() {
		ArrayList<BigDecimal> regPriceList = new ArrayList<BigDecimal>();
		for (int i = 0; i < driver.findElements(By.cssSelector(".reg-price.price-alert")).size(); i = i + 1) {
			regPriceList.add(new BigDecimal(
					driver.findElements(By.cssSelector(".reg-price.price-alert")).get(i).getText().substring(1)));
		}
		return regPriceList;

	}

	// Get the old price
	public ArrayList<BigDecimal> getOldPrice() {
		ArrayList<BigDecimal> oldPriceList = new ArrayList<BigDecimal>();
		for (int i = 0; i < driver.findElements(By.cssSelector(".old-price")).size(); i = i + 1) {
			oldPriceList.add(
					new BigDecimal(driver.findElements(By.cssSelector(".old-price")).get(i).getText().substring(1)));
		}
		return oldPriceList;
	}

	// Verify the amount on the badge and the price reduction match or not
	public Boolean isPriceReduMatched(ArrayList<BigDecimal> badgeList, ArrayList<BigDecimal> oldPriceList,
			ArrayList<BigDecimal> regPriceList) {
		for (int i = 0; i < badgeList.size() - 1; i++) {
			BigDecimal delta = oldPriceList.get(i).subtract(regPriceList.get(i));
			System.out.print(oldPriceList.get(i) + "-" + regPriceList.get(i) + "=" + delta + ", ");
			System.out.println("badge=" + badgeList.get(i));
			if (!delta.equals(badgeList.get(i))) {
				return false;
			}

		}
		return true;
	}

	// Get the price alert
	public ArrayList<String> getPriceAlert() {
		List<WebElement> priceAlert = driver.findElements(By.cssSelector(".reg-qty.price-alert"));
		ArrayList<String> priceAlertList = new ArrayList<String>();
		for (int i = 0; i < priceAlert.size(); i = i + 1) {
			String alert = priceAlert.get(i).getText();
			if (alert.contains("/ kg") && alert.contains("/ lb")) {
				priceAlertList.add(alert);
				System.out.println(alert);
			}
			// System.out.println(priceAlertList.size());

		}
		return priceAlertList;
	}

	// Verify that the price in kg is equivalent to the price in lbs.
	public Boolean isEquivalent(ArrayList<String> priceAlertList) {
		
		for (int i = 0; i < priceAlertList.size() - 1; i++) {
			// Get the priceAlert, e.g. "$7.68 / kg $3.48 / lb"
			String alert = priceAlertList.get(i);
			// Split priceAlert to priceByKg & priceByLb
			String priceByKg = alert.substring(alert.indexOf("$") + 1, alert.indexOf("/") - 1);
			String priceByLb = alert.substring(alert.lastIndexOf("$") + 1, alert.lastIndexOf("/") - 1);
			
			// Convert priceByKg to priceByLb
			BigDecimal scaled = new BigDecimal(priceByKg).multiply(new BigDecimal(0.453592)).setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
			System.out.print("priceByKg=" + new BigDecimal(priceByKg) + ", " + new BigDecimal(priceByKg) + "*"
					+ new BigDecimal(0.453592) + "=" + new BigDecimal(priceByKg).multiply(new BigDecimal(0.453592))
					+ ", ");
			System.out.print("scaled=" + scaled + ", ");
			System.out.println("priceByLb=" + priceByLb);

			// Compare priceByLb to converted priceByKg
			if (!new BigDecimal(priceByLb).equals(scaled)) {
				return false;
			}
		}

		return true;
	}

}
