package demo.features.search;

import demo.config.URLConfigConstants;
import demo.steps.serenity.SearchSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SearchFeatures {

    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @Steps
    public SearchSteps searchSteps;

    @Issue("FTP-Search-01")
    @Test
    public void searchWithValidTermShouldDisplayProducts () {
        String searchTerm = "yamaha";

        webdriver.get(URLConfigConstants.HOME_PAGE_URL);
        searchSteps.acceptCookieConsent();
        searchSteps.enterSearchTerm(searchTerm);
        searchSteps.verifySearchOutcome(); // Verifică afișarea produselor
    }

    @Test
    public void searchWithInvalidTermShouldShowNoResults () {
        String searchTerm = "notexistentproduct";

        webdriver.get(URLConfigConstants.HOME_PAGE_URL);
        searchSteps.acceptCookieConsent();
        searchSteps.enterSearchTerm(searchTerm);
        searchSteps.verifySearchOutcome(); // Verifică mesajul de eroare
    }
}