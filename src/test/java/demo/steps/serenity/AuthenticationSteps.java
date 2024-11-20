package demo.steps.serenity;

import demo.pages.Authentication_Page;
import demo.pages.SharedElements_Page;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

public class AuthenticationSteps {
    SharedElements_Page sharedPage;
    Authentication_Page authPage;

    @Step("Accept cookie consent banner")
    public void acceptCookieConsent() {
        sharedPage.acceptCookieConsent();
    }

    @Step("Login to MotoMus")
    public void loginToMotoMusServerWithCredentials(String email, String password) {
        authPage.enterEmail(email);
        authPage.enterPassword(password);
        authPage.clickLoginButton();
    }

    @Step("Verify displayed message")
    public void verifyDisplayedMessage(String expectedMessage, boolean isError) {
        String actualMessage = isError ? authPage.getErrorMessageText() : authPage.getLoginMessageText();
        Assert.assertTrue(
                "Expected message: '" + expectedMessage + "', but found: '" + actualMessage + "'",
                actualMessage.contains(expectedMessage)
        );
    }

    @Step("Logout from MotoMus account")
    public void logoutFromMotoMusAccount() {
        authPage.clickLogoutButton();
    }

    @Step("Verify redirection to homepage with URL: {0}")
    public void verifyRedirectionToHomePage(String expectedUrl) {
        String actualUrl = authPage.getCurrentUrl();
        Assert.assertEquals("Redirection URL does not match the expected URL", expectedUrl, actualUrl);
    }
}
