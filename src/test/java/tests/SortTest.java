package tests;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

import java.util.ArrayList;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.SearchResultsPage;
import pageobjects.SortPage;

public class SortTest extends Hooks {
	private HomePage home;
	private SearchResultsPage search;
	private SortPage sort;
	private ArrayList<Float> priceList;

	@Test
	public void testSort() {
		// Goto loblaw.ca
		home = new HomePage(driver);
		home.get();
		
		// Search for "apples"
		search = home.search("apples");
		search.get();
		assertTrue(search.getResult().contains("apples".toUpperCase()));
		
		// and sort the search results from highest price to lowest price
		sort = search.sort();
		sort.get();

		// Confirm that the web site has sorted the price correctly
		priceList = sort.getPrice();
		assertFalse(!sort.isDescending(priceList));

		// 2) As a new user to the site, add an item to your cart and verify it
		// was added successfully. The site should ask you to pick a store, so
		// you will need to handle that :).

		// 3) As a new user to the site, click 'Start A New Order' and select a
		// store that allows online shopping, confirm that the homepage displays
		// the correct store.
	}
}
