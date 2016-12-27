package tests;

import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.StoreLocatorPage;
import pageobjects.SearchResultsPage;

public class StoreLocatorTest extends Hooks {
	private HomePage home;
	private SearchResultsPage search;
	private StoreLocatorPage store;

	@Test
	public void testStoreLocator() {

		home = new HomePage(driver);
		home.get();

		// Search for 'apples'
		search = home.search("apples");
		search.getResult();
		
		
		store = search.add();
		//store.selectStore("M1W 2K7");
		//store.shop();
	}
}
