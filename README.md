#selenium_automation
=======
# web_automation

###Automation Take-Home Test

Purpose:

1. To measure the candidate's ability to build robust tests using the PageObject gem as a base.

2. To measure the candidate's overall code quality


Deliverables:

* Return the generated project folder in a zip file

* Include instructions on how to run the tests and install any required libraries


Required Tools in Generated Gemfile:

* Cucumber for the test framework

* Page-Object gem for the test code (it is a wrapper for Selenium & Watir WebDriver)

* Rake for launching tests / tasks

* Pry for debugging (optional)


Browser the test needs to run on:

* Chrome (Chrome driver should be installed first if not already. See installing Chrome driver abo)


Tests scenarios:

_Goto_ [loblaw.ca](https://www.loblaws.ca/)

1a) Search for `apples` and sort the search results from highest price to lowest price. Confirm that the web site has sorted the price correctly.

1b) Search for `apples` and use the _Price Reduction_ filter under _Promotions_ to sort the search by sale badges. Verify for every product: the amount on the badge and the price reduction match, and that the price in kg is equivalent to the price in lbs.

2) As a new user to the site, add an item to your cart and verify it was added successfully. The site should ask you to pick a store, so you will need to handle that :).

3) As a new user to the site, click 'Start A New Order' and select a store that allows online shopping, confirm that the homepage displays the correct store.
>>>>>>> 054ff489d9d936c43cead6b7cc4b8177a5da816b
