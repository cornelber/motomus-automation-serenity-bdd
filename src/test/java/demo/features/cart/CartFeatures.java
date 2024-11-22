package demo.features.cart;

import demo.config.URLConfigConstants;
import demo.steps.serenity.CartSteps;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import java.util.List;

@RunWith(SerenityRunner.class)
public class CartFeatures {

    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @Steps
    public CartSteps cartSteps;

    @Issue("FTP-Add-Product-01")
    @Test
    public void userCanAddRandomProductToCart() throws InterruptedException {
        webdriver.get(URLConfigConstants.ACCESSORIES_PAGE_URL);                                  // Accesăm pagina de catalog
        cartSteps.acceptCookieConsent();                                                // Acceptăm banner-ul de consimțământ pentru cookie-uri

        // Obținem și verificăm lista de produse disponibile
        List<WebElementFacade> allProducts = cartSteps.getAllProducts();
        cartSteps.verifyIfProductListIsNotEmpty(allProducts);

        // Selectăm și adăugăm un produs aleatoriu în coș
        WebElementFacade product = cartSteps.selectRandomProduct(allProducts);
        cartSteps.addProductToCart(product);

        // Așteptăm câteva secunde înainte de a deschide modalul coșului
        Thread.sleep(4000);

        // Verificăm dacă produsul a fost adăugat corect în coș
        cartSteps.openCartModal();
        cartSteps.verifyProductInCartModal(product);
    }
}
