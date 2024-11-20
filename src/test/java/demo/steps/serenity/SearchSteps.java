package demo.steps.serenity;

import demo.pages.Search_Page;
import demo.pages.SharedElements_Page;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.List;

public class SearchSteps {
    SharedElements_Page sharedPage;
    Search_Page searchPage;

    @Step("Accept cookie consent banner")
    public void acceptCookieConsent() {
        sharedPage.acceptCookieConsent();
    }

    @Step("Search for product term")
    public void enterSearchTerm(String searchTerm) {
        searchPage.openSearchModal();
        searchPage.enterSearchText(searchTerm);
        searchPage.clickSearchButton();
    }

    @Step("Verify the search outcome with keyword: {0}")
    public void verifySearchOutcome() {
        List<String> productsList = searchPage.getProductsList();

        if (productsList.isEmpty()) {
            String actualMessage = searchPage.getNotFoundSearchTitle();
            String expectedMessage = "Ne pare rau, nu am gasit produse care sa se potriveasca cu cautarea ta";
            Assert.assertTrue("Error: Expected message '" + expectedMessage + "', but found: '" + actualMessage + "'",
                    actualMessage.contains(expectedMessage));
        } else {
            Assert.assertFalse("Error: No products were displayed despite valid search term.", productsList.isEmpty());
        }
    }

}
