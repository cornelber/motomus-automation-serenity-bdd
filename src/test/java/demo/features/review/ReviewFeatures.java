package demo.features.review;

import demo.config.URLConfigConstants;
import demo.steps.serenity.ReviewSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class ReviewFeatures {

    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @Steps
    public ReviewSteps reviewSteps;


    @Issue("FTP-Add-Review-01")
    @Test
    public void userSendReviewWithValidFields() {
        webdriver.get(URLConfigConstants.SPECIFIC_PRODUCT_PAGE_URL);
        reviewSteps.acceptCookieConsent();

        reviewSteps.openReviewModal();
        reviewSteps.enterReviewDetails("test", "test@gmail.com", "Test description");
        reviewSteps.verifySuccessStatus();
    }

    @Test
    public void userSendReviewWithMissedMandatoryFields() {
        webdriver.get(URLConfigConstants.SPECIFIC_PRODUCT_PAGE_URL);
        reviewSteps.acceptCookieConsent();

        reviewSteps.openReviewModal();
        reviewSteps.enterReviewDetails("", "", "Test description");
        reviewSteps.verifyErrorStatus();
    }
}
