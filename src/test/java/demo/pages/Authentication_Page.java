package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class Authentication_Page extends PageObject {

    @FindBy(css = "input[type='email'][id='_loginEmail']")
    private WebElementFacade emailInput;

    @FindBy(css = "input[type='password'][id='_loginPassword']")
    private WebElementFacade passwordInput;

    @FindBy(css = "#doLogin")
    private WebElementFacade loginButton;

    @FindBy(css = ".side-menu a[href*='logout=1']")
    private WebElementFacade logoutButton;

    @FindBy(css = ".errorMsg")
    private WebElementFacade errorMessage;

    @FindBy(css = "h3.title")
    private WebElementFacade loginSuccessMessage;

    public void enterEmail(String email) {
        emailInput.waitUntilClickable().type(email);
    }

    public void enterPassword(String password) {
        passwordInput.waitUntilClickable().type(password);
    }

    public void clickLoginButton() {
        loginButton.waitUntilClickable().click();
    }

    public void clickLogoutButton() {
        logoutButton.waitUntilClickable().click();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public String getErrorMessageText() {
        return errorMessage.waitUntilVisible().getText();
    }

    public String getLoginMessageText() {
        return loginSuccessMessage.waitUntilVisible().getText();
    }
}
