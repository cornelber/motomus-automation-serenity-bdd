package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Review_Page extends PageObject {

    @FindBy(css = "h2[aria-controls='tab-grup_tab_item-3']")
    private WebElementFacade reviewTab;

    @FindBy(css = "a#addReview")
    private WebElementFacade addReviewButton;

    @FindBy(css = "input[name='name']")
    private WebElementFacade reviewNameField;

    @FindBy(css = "input[name='email']")
    private WebElementFacade reviewEmailField;

    @FindBy(css = "label[for='star5']")
    private WebElementFacade reviewStarFiveField;

    @FindBy(css = "textarea[name='description']")
    private WebElementFacade reviewDescriptionField;

    @FindBy(css = "input[type='checkbox'][name='agreePersonalInformation']")
    private WebElementFacade reviewAgreePersonalInformationField;

    @FindBy(css = "a#_sendReview")
    private WebElementFacade sendReviewButton;

    @FindBy(css = "div#succesReview")
    private WebElementFacade successReviewSendMessage;

    @FindBy(css = "p.hint.err-text")
    private WebElementFacade errorReviewSendMessage;

    private void switchToIframe() {
        WebElement iframe = find(By.cssSelector("iframe.fancybox-iframe"));
        getDriver().switchTo().frame(iframe);
    }

    private void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    public void openReviewTab() {
        reviewTab.waitUntilClickable().click();
    }

    public void clickAddReviewButton() {
        addReviewButton.waitUntilVisible().waitUntilClickable().click();
    }

    public void enterName(String name) {
        switchToIframe();
        reviewNameField.waitUntilVisible().waitUntilClickable().type(name);
        switchToDefaultContent();
    }

    public void enterEmail(String email) {
        switchToIframe();
        reviewEmailField.waitUntilVisible().type(email);
        switchToDefaultContent();
    }

    public void clickStarFive() {
        switchToIframe();
        reviewStarFiveField.waitUntilVisible().click();
        switchToDefaultContent();
    }

    public void enterDescription(String description) {
        switchToIframe();
        reviewDescriptionField.waitUntilVisible().type(description);
        switchToDefaultContent();
    }

    public void clickAgreePersonalInformation() {
        switchToIframe();
        reviewAgreePersonalInformationField.waitUntilVisible().click();
        switchToDefaultContent();
    }

    public void clickSendReviewButton() {
        switchToIframe();
        sendReviewButton.waitUntilVisible().waitUntilClickable().click();
        switchToDefaultContent();
    }

    public String getSuccessReviewSendMessage() {
        return successReviewSendMessage.waitUntilVisible().getText();
    }

    public String getErrorReviewSendMessage() {
        switchToIframe();
        String errorMsg = errorReviewSendMessage.waitUntilVisible().getText();
        switchToDefaultContent();
        return errorMsg;
    }
}
