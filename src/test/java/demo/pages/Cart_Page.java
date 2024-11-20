package demo.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Cart_Page extends PageObject {

    private static final String PRODUCT_SELECTOR = "div[data-block-name='ListingName']";
    private static final String ADD_TO_CART_BUTTON_SELECTOR = "a.btn-cart.add2cartList";

    @FindBy(css= "a.cart-drop._showCartHeader")
    private WebElementFacade cartModalButton;

    public List<WebElementFacade> getAllProducts() {
        List<WebElementFacade> products = findAll(By.cssSelector(PRODUCT_SELECTOR));
        System.out.println("Found products: " + products.size());
        return products;
    }

    public WebElementFacade getRandomProduct(List<WebElementFacade> productList) {
        Random random = new Random();
        return productList.get(random.nextInt(productList.size()));
    }

    public void addProductToCart(WebElementFacade product) {
        product.findBy(By.cssSelector(ADD_TO_CART_BUTTON_SELECTOR)).waitUntilClickable().click();
    }

    public WebElementFacade getCartModalButton() {
        return cartModalButton;
    }

    public void clickCartModal() {
        cartModalButton.waitUntilClickable().click();
    }

    public List<String> getProductsListFromCartModal() {
        WebElementFacade productsList = find(org.openqa.selenium.By.className("cart-items"));
        return productsList.findElements(org.openqa.selenium.By.tagName("li")).stream()
                .map(product -> product.getAttribute("data-gomagproductid"))
                .collect(Collectors.toList());
    }
}
