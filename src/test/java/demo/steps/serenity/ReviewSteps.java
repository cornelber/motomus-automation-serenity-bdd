package demo.steps.serenity;

import demo.pages.Review_Page;
import demo.pages.SharedElements_Page;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;


public class ReviewSteps {

    SharedElements_Page sharedPage;
    Review_Page reviewPage;

    @Step("Accept cookie consent banner")
    public void acceptCookieConsent() {
        sharedPage.acceptCookieConsent();
    }

    @Step("Open review modal by navigating to the review tab and clicking 'Add Review' button")
    public void openReviewModal() {
        reviewPage.openReviewTab();
        reviewPage.clickAddReviewButton();
    }

    @Step("Enter review details")
    public void enterReviewDetails(String name, String email, String description) {
        reviewPage.enterName(name);
        reviewPage.enterEmail(email);
        reviewPage.clickStarFive();
        reviewPage.enterDescription(description);
        reviewPage.clickAgreePersonalInformation();
        reviewPage.clickSendReviewButton();
    }

    @Step("Verify success message after submitting the review")
    public void verifySuccessStatus() {
        String expectedSuccessMessage = "Review-ul a fost trimis cu succes.";
        String actualMessage = reviewPage.getSuccessReviewSendMessage();

        Assert.assertTrue("Mesajul de succes nu este corect. Textul găsit: " + actualMessage,
                actualMessage.contains(expectedSuccessMessage));
    }

    @Step("Verify error message if review submission failed")
    public void verifyErrorStatus() {
        String expectedErrorMessage = "Pentru a trimite review-ul, trebuie sa completezi toate campurile";
        String actualMessage = reviewPage.getErrorReviewSendMessage();

        Assert.assertTrue("Mesajul de eroare nu este corect. Textul găsit: " + actualMessage,
                actualMessage.contains(expectedErrorMessage));
    }


}
