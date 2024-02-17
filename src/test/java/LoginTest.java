import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import edu.practicum.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.object.HomePage;
import page.object.LoginPage;
import page.object.RegistrationPage;
import static edu.practicum.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static edu.practicum.UserGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

@RunWith(Parameterized.class)
public class LoginTest {

    private User user = randomUser();
    UserMethods userApiMethod = new UserMethods();

    @Rule
    public BrowserRule rule;

    public LoginTest(BrowserRule rule) {
        this.rule = rule;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                { new YandexRule() },
                { new ChromeRule() }
        };
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = STELLAR_BURGERS_HOME_PAGE_URL;
        userApiMethod.create(user);
    }

    @Test
    @DisplayName("Вход в аккаунт по кнопке «Войти в аккаунт» на главной")
    public void inputByButtonToEnterAccountHp(){
        LoginPage login = new LoginPage(rule.getWebDriver());
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .openHomePage()
                .clickOnButtonToEnterAccountHp();
        login
                .waitingForLoading()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth()
                .checkHomePageAfterAuth();
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку «Личный кабинет» на главной")
    public void inputByPersonalAccountButtonHp(){
        LoginPage login = new LoginPage(rule.getWebDriver());
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .openHomePage()
                .clickOnPersonalAccountButtonHp();
        login
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth()
                .checkHomePageAfterAuth();
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме регистрации")
    public void inputByLoginButtonInFormRegistration(){
        RegistrationPage registration = new RegistrationPage(rule.getWebDriver());
        LoginPage login = new LoginPage(rule.getWebDriver());

        registration
                .openRegistrationPage();
        login
                .clickOnLoginButtonInForms()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .checkHomePageAfterAuth();
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме восстановления пароля")
    public void inputByLoginButtonInFormRestorePassword(){
        LoginPage login = new LoginPage(rule.getWebDriver());

        login
                .openPasswordRestorePage()
                .clickOnLoginButtonInForms()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth()
                .checkHomePageAfterAuth();
    }

    @After
    public void tearDown(){
        userApiMethod.delete(user);
        clearBrowserCookies();
    }
}
