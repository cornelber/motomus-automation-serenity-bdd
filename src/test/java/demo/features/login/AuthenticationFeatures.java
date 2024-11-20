package demo.features.login;

import demo.config.URLConfig;
import demo.steps.serenity.AuthenticationSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/features/login/data/loginTestData.csv")
public class AuthenticationFeatures {

    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webdriver;

    @Steps
    public AuthenticationSteps authSteps;

    private String email;
    private String password;
    private String expectedMessage;
    private String isError;

    @Issue("FTP-Login-Logout-01")
    @Test
    public void validateLoginAndLogoutFunctionality() {
        webdriver.get(URLConfig.REGISTER_PAGE_URL);                                        // Navigăm la pagina de login
        authSteps.acceptCookieConsent();                                                   // Acceptăm cookie
        authSteps.loginToMotoMusServerWithCredentials(email, password);                    // Executăm login cu datele parametrizate
        authSteps.verifyDisplayedMessage(expectedMessage, Boolean.parseBoolean(isError));  // Verificăm mesajul afișat

        // Dacă este un login valid, verificăm și logout-ul
        if(!Boolean.parseBoolean(isError)) {
            authSteps.logoutFromMotoMusAccount();
            authSteps.verifyRedirectionToHomePage(URLConfig.HOME_PAGE_URL);
        }
    }

}
