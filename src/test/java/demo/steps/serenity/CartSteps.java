package demo.steps.serenity;

import demo.pages.Cart_Page;
import demo.pages.SharedElements_Page;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.List;

public class CartSteps {
    private SharedElements_Page sharedPage;
    private Cart_Page cartPage;

    @Step("Accept cookie consent banner")
    public void acceptCookieConsent() {
        sharedPage.acceptCookieConsent();
    }

    @Step("Get all products from the catalog")
    public List<WebElementFacade> getAllProducts() {
        return cartPage.getAllProducts();
    }

    @Step("Verify that the product list is not empty")
    public void verifyIfProductListIsNotEmpty(List<WebElementFacade> productList) {
        Assert.assertFalse("Product list should not be empty", productList.isEmpty());
    }

    @Step("Select a random product from the catalog")
    public WebElementFacade selectRandomProduct(List<WebElementFacade> productList) {
        return cartPage.getRandomProduct(productList);
    }

    @Step("Add the selected product to the cart")
    public void addProductToCart(WebElementFacade product) {
        cartPage.addProductToCart(product);
    }

    @Step("Open cart modal to see added product")
    public void openCartModal() {
        cartPage.clickCartModal();
    }

    @Step("Verify product in the cart modal")
    public void verifyProductInCartModal(WebElementFacade productToCheck) {
        List<String> productIdList = cartPage.getProductsListFromCartModal();
        String productId = productToCheck.getAttribute("data-product-id");

        boolean productFound = productIdList.stream()
                .anyMatch(cartProductId -> cartProductId.contains(productId));

        Assert.assertTrue("Product " + productId + " was not found in the cart", productFound);
    }
}
