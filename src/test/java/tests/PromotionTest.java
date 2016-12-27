package tests;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.math.BigDecimal;

import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.SearchResultsPage;
import pageobjects.PromotionPage;

public class PromotionTest extends Hooks {
	private HomePage home;
	private SearchResultsPage search;
	private PromotionPage promo;
	private ArrayList<BigDecimal> badgeList;
	private ArrayList<BigDecimal> regPriceList;
	private ArrayList<BigDecimal> oldPriceList;

	@Test
	public void testPromotion() {
		home = new HomePage(driver);
		home.get();

		// Search for 'apples'
		search = home.search("apples");
		search.get();

		// and use the _Price Reduction_ filter under _Promotions_ to sort the
		// search by sale badges
		promo = search.promotion();
		promo.get();

		// Verify for every product: the amount on the badge and the price
		// reduction match, and that the price in kg is equivalent to the price
		// in lbs
		badgeList = promo.getBadge();
		oldPriceList = promo.getOldPrice();
		regPriceList = promo.getRegPrice();

		assertTrue(promo.isPriceReduMatched(badgeList, oldPriceList, regPriceList));
		assertTrue(promo.isEquivalent(promo.getPriceAlert()));

	}
}
