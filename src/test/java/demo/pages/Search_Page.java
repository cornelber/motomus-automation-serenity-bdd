package demo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class Search_Page extends PageObject {

    @FindBy(css = ".search-m a.-g-no-url")
    private WebElementFacade searchModalButton;

    @FindBy(css = "input[aria-label='Search'][id='_autocompleteSearchMobileToggle']")
    private WebElementFacade searchInput;

    @FindBy(css = "button#_doSearchMobile")
    private WebElementFacade searchButton;

    @FindBy(css = "h1.title")
    private WebElementFacade notFoundSearchTitle;


    public void enterSearchText(String searchText) {
        searchInput.waitUntilClickable().type(searchText);
    }

    public void openSearchModal() {
        searchModalButton.waitUntilClickable().click();
    }

    public void clickSearchButton() {
        searchButton.waitUntilClickable().click();
    }

    public String getNotFoundSearchTitle() {
        return notFoundSearchTitle.getText();
    }

    public List<String> getProductsList() {
        WebElementFacade productsList = find(By.className("product-box"));
        return productsList.findElements(By.tagName("h2")).stream()
                .map(product -> product.getText())
                .collect(Collectors.toList());
    }
}
