import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import edu.practicum.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.object.HomePage;
import page.object.LoginPage;
import page.object.PersonalAccountPage;

import static edu.practicum.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static edu.practicum.UserGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

@RunWith(Parameterized.class)
public class TransitionFromAccountTest {

    User user = randomUser();
    UserMethods userMethod = new UserMethods();

    @Rule
    public BrowserRule rule;

    public TransitionFromAccountTest(BrowserRule rule) {
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
        userMethod.create(user);

        LoginPage login = new LoginPage(rule.getWebDriver());
        login
                .openLoginPage()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор - по клику на «Конструктор»")
    public void transitionFromAccountByConstructorButton(){
        PersonalAccountPage personalAccount = new PersonalAccountPage(rule.getWebDriver());
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnPersonalAccountButtonHp();
        personalAccount
                .clickOnConstructorButton();
        homePage
                .checkConstructorHeaderText();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор - по клику на логотип Stellar Burgers")
    public void transitionFromAccountByLogo(){
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnPersonalAccountButtonHp()
                .clickOnLogoStellarBurgers()

                .checkConstructorHeaderText();
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void transitionFromAccountByExitButton()  {
        PersonalAccountPage personalAccount = new PersonalAccountPage(rule.getWebDriver());
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnPersonalAccountButtonHp();
        personalAccount
                .clickOnExitButton();

        boolean enteredTextDisplayed = LoginPage.isDisplayedEnterText();
        Assert.assertTrue("Отображается введённый текст", enteredTextDisplayed);
    }

    @After
    public void tearDown(){
        userMethod.delete(user);
        clearBrowserCookies();
    }
}