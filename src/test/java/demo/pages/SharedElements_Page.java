package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class SharedElements_Page extends PageObject {

    @FindBy(css = "#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")
    private WebElementFacade cookieConsentButton;

    public void acceptCookieConsent() {
        cookieConsentButton.waitUntilClickable().click();
    }
}
